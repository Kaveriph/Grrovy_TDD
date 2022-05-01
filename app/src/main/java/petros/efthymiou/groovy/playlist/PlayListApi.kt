package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.playlist.placeholder.PlayList

interface PlayListApi {

    suspend fun fetchAllPlaylists() : List<PlayList>
}