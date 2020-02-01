package com.pgf.mediamonks.core.api

import com.pgf.mediamonks.core.api.model.PhotoItem
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitApiService
@Inject constructor(retrofit: Retrofit) : PhotosApi {

    private val photosApi by lazy { retrofit.create(PhotosApi::class.java) }

    override fun getPhotos(): Call<List<PhotoItem>> = photosApi.getPhotos()


}
