package com.javdiana.getphotos.datasource.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.javdiana.getphotos.api.Api
import com.javdiana.getphotos.datasource.PhotoDataSource
import com.javdiana.getphotos.model.Photo
import com.javdiana.getphotos.repository.PhotosRepository
import io.reactivex.disposables.CompositeDisposable

class SearchPhotoDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val query: MutableLiveData<String>,
    private val photosRepository: PhotosRepository
) : DataSource.Factory<Int, Photo>() {
    val photosLiveData = MutableLiveData<PhotoDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val searchPhotoDataSource =
            PhotoDataSource(
                photosRepository,
                compositeDisposable,
                query,
                true
            )
        photosLiveData.postValue(searchPhotoDataSource)
        return searchPhotoDataSource
    }
}