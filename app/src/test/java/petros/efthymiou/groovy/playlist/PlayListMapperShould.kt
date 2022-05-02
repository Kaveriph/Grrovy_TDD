package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.playlist.placeholder.PlayList
import petros.efthymiou.groovy.playlist.placeholder.PlayListRaw

class PlayListMapperShould : BaseUnitTest() {
    private val playlistRaw = listOf<PlayListRaw>(PlayListRaw( "1", "Hard Rock Cafe", "rock", 1),
        PlayListRaw( "2", "Hard Rock Cafe", "jazz", 1),
        PlayListRaw( "3", "Hard Rock Cafe", "rock", 1),
        PlayListRaw( "4", "Hard Rock Cafe", "mixed", 1))
    private val playListMapper = PlayListMapper()
    private val playListExpected = listOf<PlayList>(PlayList( "1", "Hard Rock Cafe", "rock", R.mipmap.rock),
        PlayList( "2", "Hard Rock Cafe", "jazz", R.mipmap.playlist),
        PlayList( "3", "Hard Rock Cafe", "rock", R.mipmap.rock),
        PlayList( "4", "Hard Rock Cafe", "mixed", R.mipmap.playlist))

    @Test
    fun convertPlayListRawToPlayList() {
        val playlist = playListMapper.convert(playlistRaw)
        assertEquals(playListExpected, playlist)
    }

}