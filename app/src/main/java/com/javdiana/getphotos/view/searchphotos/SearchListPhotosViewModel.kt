package com.javdiana.getphotos.view.searchphotos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.javdiana.getphotos.api.api.ApiService
import com.javdiana.getphotos.datasource.search.SearchPhotoDataSourceFactory
import com.javdiana.getphotos.model.Photo
import io.reactivex.disposables.CompositeDisposable

class SearchListPhotosViewModel : ViewModel() {
    var searchPhotos: LiveData<PagedList<Photo>>
    private val photosService = ApiService.INSTANCE
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 30
    private lateinit var searchPhotosDataSourceFactory: SearchPhotoDataSourceFactory
    private val query = MutableLiveData<String>()

    init {
        searchPhotos = getList()
    }

    fun retry() {
        searchPhotosDataSourceFactory.searchPhotosLiveData.value?.retry()
    }

    private fun getList(): LiveData<PagedList<Photo>> {
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
        return LivePagedListBuilder<Int, Photo>(searchPhotosDataSourceFactory, config).build()
    }

    fun search(query: String) {
        this.query.value = query
        searchPhotosDataSourceFactory.searchPhotosLiveData.value?.invalidate()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}