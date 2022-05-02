package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.playlist.placeholder.PlayList
import petros.efthymiou.groovy.utils.captureValues
import petros.efthymiou.groovy.utils.getValueForTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlayListViewModelShould : BaseUnitTest() {

    private val exception = RuntimeException("Something went wrong!")
    private val repository: PlayListRepository = mock()
    private val playlists: List<PlayList> = mock()
    private val expected = Result.success(playlists)

    @Test
    fun getPlayListsFromRepository() = runBlockingTest {
        val viewModel = mockSuccessFullCase()
        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlayLists()
    }

    @Test
    fun emitsPlayListsFromRepository() = runBlockingTest {
        val viewModel = mockSuccessFullCase()
        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError() {
        mockErrorCase()
        val viewModel = PlayListViewModel(repository)
        assertEquals(exception, viewModel.playlists.getValueForTest()?.exceptionOrNull())
    }

    private fun mockErrorCase() {
        runBlocking {
            whenever(repository.getPlayLists()).thenReturn(flow {
                emit(Result.failure<List<PlayList>>(exception = exception))
            })
        }
    }

    @Test
    fun emitLoadingStarted() {
        val viewModel = mockSuccessFullCase()
        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(true, values[0])
        }
    }

    @Test
    fun emitLoadingDismissed() = runBlockingTest {
        val viewModel = mockSuccessFullCase()
        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(false, values[1])
        }
    }

    private fun mockSuccessFullCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlayLists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        val viewModel = PlayListViewModel(repository)
        return viewModel
    }


}