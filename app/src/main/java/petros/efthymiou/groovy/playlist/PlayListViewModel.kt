package petros.efthymiou.groovy.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import petros.efthymiou.groovy.playlist.placeholder.PlayList

class PlayListViewModel(private val repository: PlayListRepository) : ViewModel() {

    /**
     * Using livedata builder to populate the values in livedata.
     * here it is not mutablelivedata. hence outside world cannot modify the contents also.
     * */
    val playlists = liveData<Result<List<PlayList>>>() {
        this.emitSource(repository.getPlayLists().asLiveData())
    }

}
