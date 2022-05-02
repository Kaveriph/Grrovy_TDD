package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import petros.efthymiou.groovy.playlist.placeholder.PlayList
import javax.inject.Inject

class PlayListRepository @Inject constructor(
    private val playListService: PlayListService,
    private val playListMapper: PlayListMapper
) {

    suspend fun getPlayLists(): Flow<Result<List<PlayList>>> {
        val playListFlow = playListService.fetchPlaylists()
        if(playListFlow.first().isSuccess) {
            val playListRaw = playListFlow.first().getOrNull()
            val playList = playListMapper.convert(playlistRaw = playListRaw!!)
            return flow { emit(Result.success(playList)) }
        } else {
            return flow { emit(Result.failure(playListFlow.first().exceptionOrNull()!!))}
        }
    }

}
