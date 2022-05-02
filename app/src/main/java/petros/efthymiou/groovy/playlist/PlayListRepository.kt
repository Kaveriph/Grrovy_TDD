package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.*
import petros.efthymiou.groovy.playlist.placeholder.PlayList
import javax.inject.Inject

class PlayListRepository @Inject constructor(
    private val playListService: PlayListService,
    private val playListMapper: PlayListMapper
) {

    suspend fun getPlayLists(): Flow<Result<List<PlayList>>> {
        val playListFlow = playListService.fetchPlaylists()
        /*return playListFlow.mapLatest {
            Result.success(playListMapper(it.getOrNull()!!))
            *//*if(it.isSuccess) Result.success(playListMapper(it.getOrNull()!!))
            else Result.failure(it.exceptionOrNull()!!)*//*
        }*/
        if(playListFlow.first().isSuccess) {
            val playList = playListMapper.invoke(playlistRaw = playListFlow.first().getOrNull()!!)
            return flow { emit(Result.success(playList)) }
        } else {
            return flow { emit(Result.failure(playListFlow.first().exceptionOrNull()!!))}
        }
    }

}
