package petros.efthymiou.groovy.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.playlist.placeholder.PlayList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A fragment representing a list of Items.
 */
class PlayListFragment : Fragment() {

    lateinit var viewModel: PlayListViewModel
    lateinit var viewModelFactory: PlayListViewmodelFactory
    private val playListApi = object : PlayListApi {
        override suspend fun fetchAllPlaylists(): List<PlayList> {
            return listOf()
        }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:2999/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(PlayListApi::class.java)

    private val playlistService = PlayListService(api)

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
        viewModelFactory = PlayListViewmodelFactory(PlayListRepository(playlistService))
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayListViewModel::class.java)
    }

    private fun observeLivedata() {
        viewModel.playlists.observe(this) { playLists ->
            if(!playLists.getOrNull().isNullOrEmpty()) {
                setupList(playLists.getOrNull()!!)
            } else {
                println("Playlists are null")
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