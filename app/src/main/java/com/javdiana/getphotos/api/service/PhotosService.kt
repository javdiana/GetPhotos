package com.javdiana.getphotos.api.service

import com.javdiana.getphotos.api.PhotosApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PhotosService {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Utils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    companion object {
        fun getInstance(): PhotosService {
            return PhotosService()
        }
    }

    fun getApi(): PhotosApi {
        return retrofit.create(PhotosApi::class.java)
    }

}