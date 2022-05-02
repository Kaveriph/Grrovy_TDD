package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import petros.efthymiou.groovy.playlist.placeholder.PlayList
import javax.inject.Inject

class PlayListRepository @Inject constructor(private val playListService: PlayListService) {
    suspend fun getPlayLists(): Flow<Result<List<PlayList>>> {
       return playListService.fetchPlaylists()
    }

}
