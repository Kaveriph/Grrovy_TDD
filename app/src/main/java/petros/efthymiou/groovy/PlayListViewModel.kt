package petros.efthymiou.groovy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import petros.efthymiou.groovy.placeholder.PlayList

class PlayListViewModel(private val repository: PlayListRepository) : ViewModel() {
    val playlists = MutableLiveData<Result<List<PlayList>>>()

    init {
        repository.getPlayLists()
    }
}