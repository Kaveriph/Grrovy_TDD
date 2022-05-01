package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.playlist.placeholder.PlayList
import java.lang.RuntimeException

class PlayListRepositoryShould : BaseUnitTest() {


    private val exception = RuntimeException("Something went wrong")
    private val playlist = mock<List<PlayList>>()
    private val service: PlayListService = mock()
    private val repository = PlayListRepository(service)

    @Test
    fun getPlaylistsService() = runBlockingTest {
        repository.getPlayLists()
        verify(service, times(1)).fetchPlaylists()
    }

    @Test
    fun emitPlayListsFromService() = runBlockingTest {
        mockSuccessFullCase()
        val playlists = repository.getPlayLists()
        assertEquals(playlist, playlists.first().getOrNull())
    }


    @Test
    fun propogateErrors() = runBlockingTest {
        mockFailureCase()
        assertEquals(exception, repository.getPlayLists().first().exceptionOrNull())
    }

    private suspend fun mockFailureCase() {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.failure<List<PlayList>>(exception))
            }
        )
    }

    private suspend fun mockSuccessFullCase() {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.success(playlist))
            }
        )
    }
}