package petros.efthymiou.groovy

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import petros.efthymiou.groovy.placeholder.PlayList

class PlayListRepository {
    suspend fun getPlayLists(): Flow<Result<List<PlayList>>> {
        return flow {
        }
    }

}
