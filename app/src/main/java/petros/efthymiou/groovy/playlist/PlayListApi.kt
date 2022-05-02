package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.playlist.placeholder.PlayListRaw
import retrofit2.http.GET

interface PlayListApi {

    @GET("playlists")
    suspend fun fetchAllPlaylists() : List<PlayListRaw>
}