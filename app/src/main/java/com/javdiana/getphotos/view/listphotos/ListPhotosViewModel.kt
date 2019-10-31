package com.javdiana.getphotos.view.listphotos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.javdiana.getphotos.api.service.PhotosService
import com.javdiana.getphotos.model.Photo
import io.reactivex.disposables.CompositeDisposable


class ListPhotosViewModel : ViewModel() {
    var photos: LiveData<PagedList<Photo>>
    private val photosService = PhotosService.getInstance()
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 30
    private val photosDataSourceFactory: PhotosDataSourceFactory

    init {
        photosDataSourceFactory = PhotosDataSourceFactory(compositeDisposable, photosService)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        photos = LivePagedListBuilder<Int, Photo>(photosDataSourceFactory, config).build()
    }

    fun retry() {
        photosDataSourceFactory.photosLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return photos.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}