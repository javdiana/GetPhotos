package com.javdiana.getphotos.api.service

import com.javdiana.getphotos.api.PhotosApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object PhotosService {
    val instance: PhotosApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return@lazy retrofit.create(PhotosApi::class.java)
    }
}