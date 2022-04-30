package petros.efthymiou.groovy.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.playlist.placeholder.PlayList

/**
 * A fragment representing a list of Items.
 */
class PlayListFragment : Fragment() {

    lateinit var viewModel: PlayListViewModel
    lateinit var viewModelFactory: PlayListViewmodelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setupViewModel()
        observeLivedata()

        return view
    }

    private fun setupViewModel() {
        viewModelFactory = PlayListViewmodelFactory(PlayListRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayListViewModel::class.java)
    }

    private fun observeLivedata() {
        viewModel.playlists.observe(this as LifecycleOwner) { playLists ->
            if(playLists.getOrNull() != null) {
                setupList(playLists.getOrNull()!!)
            } else {
                // ToDo
            }
        }
    }

    private fun setupList(playLists: List<PlayList>) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlayListRecyclerViewAdapter(playLists)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlayListFragment()
    }
}