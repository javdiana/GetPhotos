package com.javdiana.getphotos.view.listphotos

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.javdiana.getphotos.api.service.PhotosService
import com.javdiana.getphotos.model.Photo
import io.reactivex.disposables.CompositeDisposable

class PhotosDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val photosService: PhotosService
) : DataSource.Factory<Int, Photo>() {
    val photosLiveData = MutableLiveData<PhotoDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val photosDataSource = PhotoDataSource(photosService, compositeDisposable)
        photosLiveData.postValue(photosDataSource)
        return photosDataSource
    }

}