package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PlayListRepositoryShould : BaseUnitTest() {


    private val service: PlayListService = mock()
    private val repository = PlayListRepository(service)

    @Test
    fun getPlaylistsService() = runBlockingTest {
        repository.getPlayLists()
        verify(service, times(1)).fetchPlaylists()
    }
}