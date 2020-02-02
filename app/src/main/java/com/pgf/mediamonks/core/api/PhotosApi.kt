package com.pgf.mediamonks.core.api

import com.pgf.mediamonks.core.api.model.PhotoItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {

    @GET("photos")
    fun getPhotos(@Query("albumId") albumId: Int): Call<List<PhotoItem>>

}
