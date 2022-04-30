package petros.efthymiou.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import petros.efthymiou.groovy.placeholder.PlayList
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule
import petros.efthymiou.groovy.utils.getValueForTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlayListViewModelShould {


    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: PlayListRepository = mock()
    private val playlists:List<PlayList> = mock()
    private val expected = Result.success(playlists)

    @Test
    fun getPlayListsFromRepository() = runBlockingTest {
        val viewModel = mockSuccesFullCase()
        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlayLists()
    }

    @Test
    fun emitsPlayListsFromRepository() = runBlockingTest {
        val viewModel = mockSuccesFullCase()
        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    private fun mockSuccesFullCase(): PlayListViewModel {
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