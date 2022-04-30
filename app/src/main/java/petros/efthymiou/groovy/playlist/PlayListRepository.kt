package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import petros.efthymiou.groovy.playlist.placeholder.PlayList

class PlayListRepository {
    suspend fun getPlayLists(): Flow<Result<List<PlayList>>> {
        return flow {
        }
    }

}
