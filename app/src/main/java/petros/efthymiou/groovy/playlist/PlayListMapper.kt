package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.playlist.placeholder.PlayList
import petros.efthymiou.groovy.playlist.placeholder.PlayListRaw
import javax.inject.Inject

class PlayListMapper @Inject constructor() : Function1<List<PlayListRaw>, List<PlayList>> {

    override fun invoke(playlistRaw: List<PlayListRaw>): List<PlayList> {
        return playlistRaw.map { item ->
            PlayList(  item.id, item.name, item.category,
                if (item.category.equals("rock")) R.mipmap.rock else R.mipmap.playlist
            )
        }
    }

}
