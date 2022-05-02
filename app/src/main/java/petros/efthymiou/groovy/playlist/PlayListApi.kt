package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.playlist.placeholder.PlayList
import retrofit2.http.GET

interface PlayListApi {

    @GET("playlists")
    suspend fun fetchAllPlaylists() : List<PlayList>
}