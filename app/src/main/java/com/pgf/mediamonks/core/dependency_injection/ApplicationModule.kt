package com.pgf.mediamonks.core.dependency_injection

import android.content.Context
import com.pgf.mediamonks.BuildConfig
import com.pgf.mediamonks.core.api.PhotosRepository
import com.pgf.mediamonks.core.dependency_injection.AppApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AppApplication) {

    // https://jsonplaceholder.typicode.com
    private val URL_BASE = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(URL_BASE)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun providePhotosRepository(dataSourceNetwork: PhotosRepository.DataSourceNetwork): PhotosRepository = dataSourceNetwork

}