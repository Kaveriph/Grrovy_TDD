package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.playlist.placeholder.PlayList
import java.lang.RuntimeException

class PlayListServiceShould : BaseUnitTest() {

    private val exception = RuntimeException("No Playlists found!")
    private val playListApi:PlayListApi = mock()
    private val playListService = PlayListService(playListApi)
    private val playLists = mock<List<PlayList>>()

    @Test
    fun invokeFetchAllPlaylists() = runBlockingTest {
        val playlists = playListService.fetchPlaylists()
        verify(playListApi, times(1)).fetchAllPlaylists()
    }

    @Test
    fun fetchListOfPlay() = runBlockingTest {
        whenever(playListApi.fetchAllPlaylists()).thenReturn(playLists)
        assertEquals(Result.success(playLists), playListService.fetchPlaylists().first())
    }

    @Test
    fun emitErrorWhenNoPlayListFound() = runBlockingTest {
        whenever(playListApi.fetchAllPlaylists()).thenReturn(null)
        assertEquals(exception.message, playListService.fetchPlaylists().first().exceptionOrNull()?.message)
    }

    @Test
    fun emitExceptionWhenNetworkFails() = runBlockingTest {
        whenever(playListApi.fetchAllPlaylists()).thenThrow(RuntimeException("Something went wrong"))
        assertEquals("Something went wrong", playListService.fetchPlaylists().first().exceptionOrNull()?.message)
    }
}