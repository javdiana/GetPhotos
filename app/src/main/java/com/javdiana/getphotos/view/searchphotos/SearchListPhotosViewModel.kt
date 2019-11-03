package com.javdiana.getphotos.view.searchphotos

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.javdiana.getphotos.api.service.PhotosService
import com.javdiana.getphotos.datasource.search.SearchPhotoDataSourceFactory
import com.javdiana.getphotos.model.Photo
import com.javdiana.getphotos.view.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import retrofit2.http.Query

class SearchListPhotosViewModel() : BaseViewModel() {
    var query = ""
    lateinit var searchPhotos: LiveData<PagedList<Photo>>
    private val photosService = PhotosService.getInstance()
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 30
    private lateinit var searchPhotosDataSourceFactory: SearchPhotoDataSourceFactory

    fun configDataSourceFactory(){
        searchPhotosDataSourceFactory = SearchPhotoDataSourceFactory(
            compositeDisposable,
            photosService,
            query
        )
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        searchPhotos =
            LivePagedListBuilder<Int, Photo>(searchPhotosDataSourceFactory, config).build()
    }

    fun retry() {
        searchPhotosDataSourceFactory.searchPhotosLiveData.value?.retry()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}