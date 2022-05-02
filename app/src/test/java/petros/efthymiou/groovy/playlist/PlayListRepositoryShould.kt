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
import petros.efthymiou.groovy.playlist.placeholder.PlayListRaw
import java.lang.RuntimeException

class PlayListRepositoryShould : BaseUnitTest() {


    private val exception = RuntimeException("Something went wrong")
    private val playlist = mock<List<PlayList>>()
    private val service: PlayListService = mock()
    private val playlistRaw = mock<List<PlayListRaw>>()
    private val playListMapper:PlayListMapper = mock()
    private val repository = PlayListRepository(service, playListMapper)

    @Test
    fun getPlaylistsService() = runBlockingTest {
        mockSuccessFullCase()
        repository.getPlayLists()
        verify(service, times(1)).fetchPlaylists()
    }

    @Test
    fun emitMappedPlayListsFromService() = runBlockingTest {
        mockSuccessFullCase()
        val playlists = repository.getPlayLists()
        assertEquals(playlist, playlists.first().getOrNull())
    }


    @Test
    fun propogateErrors() = runBlockingTest {
        mockFailureCase()
        assertEquals(exception, repository.getPlayLists().first().exceptionOrNull())
    }

    @Test
    fun callMapperToConvertPlayListRawToPlayList() = runBlockingTest {
        mockSuccessFullCase()
        repository.getPlayLists()
        verify(playListMapper, times(1)).invoke(playlistRaw)
    }

    private suspend fun mockFailureCase() {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.failure<List<PlayListRaw>>(exception))
            }
        )
    }

    private suspend fun mockSuccessFullCase() {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.success(playlistRaw))
            }
        )
        whenever(playListMapper.invoke(playlistRaw)).thenReturn(playlist)
    }
}