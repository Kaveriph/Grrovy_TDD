package petros.efthymiou.groovy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayListViewmodelFactory(val repository: PlayListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayListViewModel(repository) as T
    }

}
