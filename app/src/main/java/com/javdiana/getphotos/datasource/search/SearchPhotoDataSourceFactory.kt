package com.javdiana.getphotos.datasource.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.javdiana.getphotos.api.Api
import com.javdiana.getphotos.model.Photo
import io.reactivex.disposables.CompositeDisposable

class SearchPhotoDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val service: Api,
    private val query: MutableLiveData<String>
) : DataSource.Factory<Int, Photo>() {
    val searchPhotosLiveData = MutableLiveData<SearchPhotoDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val searchPhotoDataSource =
            SearchPhotoDataSource(
                service,
                compositeDisposable,
                query
            )
        searchPhotosLiveData.postValue(searchPhotoDataSource)
        return searchPhotoDataSource
    }
}