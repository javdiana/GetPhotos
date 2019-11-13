package com.javdiana.getphotos.repository

import com.javdiana.getphotos.api.Api
import com.javdiana.getphotos.api.api.ApiConstants

class PhotosRepository(private val api: Api) {

    fun getPhotos(pages: Int) = api.getPhotos(ApiConstants.API_KEY, NUMBER_PHOTOS, pages)
    fun searchPhotos(query: String, pages: Int) =
        api.getSearchedPhotos(query, ApiConstants.API_KEY, NUMBER_PHOTOS,pages)

    companion object {
        const val NUMBER_PHOTOS = 30
    }
}