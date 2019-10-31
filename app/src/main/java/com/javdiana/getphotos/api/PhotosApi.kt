package com.javdiana.getphotos.api

import com.javdiana.getphotos.model.Photo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {
    @GET("photos")
    fun getPhotos(
        @Query("client_id") clientId: String
    ): Observable<ArrayList<Photo>>
}