package com.javdiana.getphotos.datasource.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.javdiana.getphotos.api.PhotosApi
import com.javdiana.getphotos.api.service.PhotosService
import com.javdiana.getphotos.model.Photo
import io.reactivex.disposables.CompositeDisposable

class SearchPhotoDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val photosService: PhotosApi,
    private val query: MutableLiveData<String>
) : DataSource.Factory<Int, Photo>() {
    val searchPhotosLiveData = MutableLiveData<SearchPhotoDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val searchPhotoDataSource =
            SearchPhotoDataSource(
                photosService,
                compositeDisposable,
                query
            )
        searchPhotosLiveData.postValue(searchPhotoDataSource)
        return searchPhotoDataSource
    }
}