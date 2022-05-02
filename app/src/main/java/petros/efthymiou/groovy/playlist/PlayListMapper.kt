package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.playlist.placeholder.PlayList
import petros.efthymiou.groovy.playlist.placeholder.PlayListRaw
import javax.inject.Inject

class PlayListMapper @Inject constructor() {
    fun convert(playlistRaw: List<PlayListRaw>): List<PlayList> {
        val playList = mutableListOf<PlayList>()
        for (item in playlistRaw) {
            playList.add(PlayList(item.id, item.name, item.category,
                if (item.category.equals("rock")) R.mipmap.rock else  R.mipmap.playlist))
        }
        return playList
    }


}
