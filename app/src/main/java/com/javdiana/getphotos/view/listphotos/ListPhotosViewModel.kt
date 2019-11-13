package com.javdiana.getphotos.view.listphotos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.javdiana.getphotos.api.api.ApiService
import com.javdiana.getphotos.datasource.photos.PhotosDataSourceFactory
import com.javdiana.getphotos.model.Photo
import com.javdiana.getphotos.repository.PhotosRepository
import io.reactivex.disposables.CompositeDisposable


class ListPhotosViewModel : ViewModel() {
    var photos: LiveData<PagedList<Photo>>
    private val photosApi = ApiService.INSTANCE
    private val compositeDisposable = CompositeDisposable()
    private var photosRepository: PhotosRepository
    private lateinit var photosDataSourceFactory: PhotosDataSourceFactory

    init {
        photosRepository = PhotosRepository(photosApi)
        photos = getList()

    }

    private fun getList(): LiveData<PagedList<Photo>> {
        photosDataSourceFactory = PhotosDataSourceFactory(
            compositeDisposable,
            photosRepository
        )
        val config = PagedList.Config.Builder()
            .setPageSize(PhotosRepository.NUMBER_PHOTOS)
            .setInitialLoadSizeHint(PhotosRepository.NUMBER_PHOTOS * 2)
            .setEnablePlaceholders(false)
            .build()
        return LivePagedListBuilder<Int, Photo>(photosDataSourceFactory, config).build()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun loadPhotos() {
        photosDataSourceFactory.photosLiveData.value?.invalidate()
        photos = getList()
    }

}