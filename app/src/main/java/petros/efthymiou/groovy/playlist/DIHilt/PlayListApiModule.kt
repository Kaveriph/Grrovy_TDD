package petros.efthymiou.groovy.playlist.DIHilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import petros.efthymiou.groovy.playlist.PlayListApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class PlayListApiModule {

    @Provides
    fun playListApi(retrofit: Retrofit): PlayListApi = retrofit.create(PlayListApi::class.java)

    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:2999/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}