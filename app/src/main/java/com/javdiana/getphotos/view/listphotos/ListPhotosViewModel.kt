package com.javdiana.getphotos.view.listphotos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.javdiana.getphotos.api.service.PhotosService
import com.javdiana.getphotos.datasource.photos.PhotosDataSourceFactory
import com.javdiana.getphotos.model.Photo
import io.reactivex.disposables.CompositeDisposable


class ListPhotosViewModel : ViewModel() {
    lateinit var photos: LiveData<PagedList<Photo>>
    private val photosService = PhotosService.instance
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 30
    private lateinit var photosDataSourceFactory: PhotosDataSourceFactory

    init {
        loadPhotos()
    }

    private fun getList(): LiveData<PagedList<Photo>> {
        photosDataSourceFactory = PhotosDataSourceFactory(
            compositeDisposable,
            photosService
        )
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        return LivePagedListBuilder<Int, Photo>(photosDataSourceFactory, config).build()
    }

    fun retry() {
        photosDataSourceFactory.photosLiveData.value?.retry()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun loadPhotos() {
        photos = getList()
    }

}