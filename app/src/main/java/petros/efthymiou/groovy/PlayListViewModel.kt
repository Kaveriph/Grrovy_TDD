package petros.efthymiou.groovy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import petros.efthymiou.groovy.placeholder.PlayList

class PlayListViewModel(private val repository: PlayListRepository) : ViewModel() {
    val playlists = MutableLiveData<Result<List<PlayList>>>()

    init {
        viewModelScope.launch {
            repository.getPlayLists().collect {
                playlists.postValue(it)
            }
        }
    }
}
