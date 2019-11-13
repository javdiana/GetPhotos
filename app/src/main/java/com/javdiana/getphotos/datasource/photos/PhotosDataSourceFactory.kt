package com.javdiana.getphotos.datasource.photos

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.javdiana.getphotos.datasource.PhotoDataSource
import com.javdiana.getphotos.model.Photo
import com.javdiana.getphotos.repository.PhotosRepository
import io.reactivex.disposables.CompositeDisposable

class PhotosDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val photosRepository: PhotosRepository
) : DataSource.Factory<Int, Photo>() {
    val photosLiveData = MutableLiveData<PhotoDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val searchPhotoDataSource =
            PhotoDataSource(
                photosRepository,
                compositeDisposable,
                MutableLiveData(),
                false
            )
        photosLiveData.postValue(searchPhotoDataSource)
        return searchPhotoDataSource
    }

}