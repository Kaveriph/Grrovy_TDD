package petros.efthymiou.groovy.playlist.DIHilt

import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import petros.efthymiou.groovy.playlist.PlayListApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

@Module
@InstallIn(FragmentComponent::class)
class PlayListApiModule {

    @Provides
    fun playListApi(retrofit: Retrofit): PlayListApi = retrofit.create(PlayListApi::class.java)

    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:2999/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}