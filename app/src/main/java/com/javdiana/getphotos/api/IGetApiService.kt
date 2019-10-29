package com.javdiana.getphotos.api

import com.javdiana.getphotos.model.Photo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IGetApiService {
    @GET("/photos")
    fun getPhotos(): Observable<Photo>

    @GET("/search/photos")
    fun searchPhotos(@Query("query") query: String): Observable<Photo>

}
