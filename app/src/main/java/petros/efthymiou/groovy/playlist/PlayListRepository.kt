package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import petros.efthymiou.groovy.playlist.placeholder.PlayList

class PlayListRepository(private val playListService: PlayListService) {
    suspend fun getPlayLists(): Flow<Result<List<PlayList>>> {
       playListService.fetchPlaylists()
        return flow {

        }
    }

}
