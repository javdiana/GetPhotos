package com.javdiana.getphotos.api

import com.javdiana.getphotos.model.Photo
import com.javdiana.getphotos.model.Result
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("photos")
    fun getPhotos(
        @Query("client_id") clientId: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Single<ArrayList<Photo>>

    @GET("search/photos")
    fun getSearchedPhotos(
        @Query("query") query: String?,
        @Query("client_id") clientId: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Single<Result>
}