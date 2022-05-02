package petros.efthymiou.groovy.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.onEach
import petros.efthymiou.groovy.playlist.placeholder.PlayList

class PlayListViewModel(private val repository: PlayListRepository) : ViewModel() {

    /**
     * Using livedata builder to populate the values in livedata.
     * here it is not mutablelivedata. hence outside world cannot modify the contents also.
     * */
    val loader = MutableLiveData<Boolean>()
    val playlists = liveData<Result<List<PlayList>>>() {
        loader.value = true
        this.emitSource(repository.getPlayLists()
            .onEach {
                loader.value = false
            }.asLiveData())
    }

}
