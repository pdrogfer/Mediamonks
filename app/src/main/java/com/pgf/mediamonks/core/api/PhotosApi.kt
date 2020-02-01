package com.pgf.mediamonks.core.api

import com.pgf.mediamonks.core.api.model.PhotoItem
import retrofit2.Call
import retrofit2.http.GET

interface PhotosApi {

    @GET("photos")
    fun getPhotos(): Call<List<PhotoItem>>

}
