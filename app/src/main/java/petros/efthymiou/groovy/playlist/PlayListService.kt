package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import petros.efthymiou.groovy.playlist.placeholder.PlayList
import petros.efthymiou.groovy.playlist.placeholder.PlayListRaw
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class PlayListService @Inject constructor(private val playListApi:PlayListApi) {
    suspend fun fetchPlaylists(): Flow<Result<List<PlayListRaw>>> {
        try {
            val playList = playListApi.fetchAllPlaylists()
            return if (playList == null) {
                flow {
                    emit(Result.failure<List<PlayListRaw>>(RuntimeException("No Playlists found!")))
                }
            } else {
                flow {
                    emit(Result.success<List<PlayListRaw>>(playList))
                }
            }
        } catch (exception : Exception) {
            println("exception : ${exception.message} ")
            println("exception : ${exception.printStackTrace()}")
            return flow {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        }
    }
}