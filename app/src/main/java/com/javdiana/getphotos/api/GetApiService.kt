package com.javdiana.getphotos.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.javdiana.getphotos.model.Photo
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class GetApiService() {

    companion object {
        lateinit var api: IGetApiService
        const val BASE_URL = "https://api.unsplash.com"
        const val LIST_PHOTOS = "https://monosnap.com/file/WBsiWXn0rFNHFSk2zwlH06yvLdcWrm"
        const val SEARCH_PHOTOS = "https://monosnap.com/file/9I7eBsKqDInK6M9KzAyJlb8S9UgsGW"
        const val API_KEY = "4c9fbfbbd92c17a2e95081cec370b4511659666240eb4db9416c40c641ee843b"
    }

    fun create() {
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()

        api = retrofit.create(IGetApiService::class.java)

    }

    fun getPhoto(): Observable<Photo> {
        create()
        return api.getPhotos()
    }
}