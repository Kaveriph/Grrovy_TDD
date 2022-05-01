package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import petros.efthymiou.groovy.playlist.placeholder.PlayList
import java.lang.Exception
import java.lang.RuntimeException

class PlayListService(private val playListApi:PlayListApi) {
    suspend fun fetchPlaylists(): Flow<Result<List<PlayList>>> {
        try {
            val playList = playListApi.fetchAllPlaylists()
            return if (playList == null) {
                flow {
                    emit(Result.failure<List<PlayList>>(RuntimeException("No Playlists found!")))
                }
            } else {
                flow {
                    emit(Result.success<List<PlayList>>(playList))
                }
            }
        } catch (exception : Exception) {
            return flow {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        }
    }
}