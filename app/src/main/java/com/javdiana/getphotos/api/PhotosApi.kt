package com.javdiana.getphotos.api

import com.javdiana.getphotos.model.Photo
import com.javdiana.getphotos.model.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {
    @GET("photos")
    fun getPhotos(
        @Query("client_id") clientId: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Observable<ArrayList<Photo>>

    @GET("search/photos")

    fun getSearchedPhotos(
        @Query("query") query: String,
        @Query("client_id") clientId: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Observable<Result>
}