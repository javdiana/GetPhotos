package com.javdiana.getphotos.view.searchphotos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.javdiana.getphotos.api.api.ApiService
import com.javdiana.getphotos.datasource.search.SearchPhotoDataSourceFactory
import com.javdiana.getphotos.model.Photo
import com.javdiana.getphotos.repository.PhotosRepository
import io.reactivex.disposables.CompositeDisposable

class SearchListPhotosViewModel : ViewModel() {
    var searchPhotos: LiveData<PagedList<Photo>>
    private val photosApi = ApiService.INSTANCE
    private val compositeDisposable = CompositeDisposable()
    private lateinit var searchPhotosDataSourceFactory: SearchPhotoDataSourceFactory
    private var photosRepository: PhotosRepository
    private val query = MutableLiveData<String>()

    init {
        photosRepository = PhotosRepository(photosApi)
        searchPhotos = getList()
    }

    private fun getList(): LiveData<PagedList<Photo>> {
        searchPhotosDataSourceFactory = SearchPhotoDataSourceFactory(
            compositeDisposable,
            query,
            photosRepository
        )
        val config = PagedList.Config.Builder()
            .setPageSize(PhotosRepository.NUMBER_PHOTOS)
            .setInitialLoadSizeHint(PhotosRepository.NUMBER_PHOTOS)
            .setEnablePlaceholders(false)
            .build()
        return LivePagedListBuilder<Int, Photo>(searchPhotosDataSourceFactory, config).build()
    }

    fun search(query: String) {
        this.query.value = query
        searchPhotosDataSourceFactory.photosLiveData.value?.invalidate()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}