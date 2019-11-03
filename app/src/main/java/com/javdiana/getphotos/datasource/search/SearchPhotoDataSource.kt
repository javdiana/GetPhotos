package com.javdiana.getphotos.datasource.search

import androidx.paging.PageKeyedDataSource
import com.javdiana.getphotos.api.service.PhotosService
import com.javdiana.getphotos.api.service.Utils
import com.javdiana.getphotos.model.Photo
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchPhotoDataSource(
    private val photosService: PhotosService,
    private val compositeDisposable: CompositeDisposable,
    private val query: String
) : PageKeyedDataSource<Int, Photo>() {
    private var retryCompletable: Completable? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        println(query)
        compositeDisposable.add(
                    photosService.getApi().getSearchedPhotos(
                        query,
                        Utils.API_KEY,
                        NUMBER_PHOTOS,
                        1
            )
                .subscribe { response ->
                    callback.onResult(response.results(), null, 2)
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        compositeDisposable.add(
            photosService.getApi().getSearchedPhotos(
                query, Utils.API_KEY,
                NUMBER_PHOTOS, params.key
            )
                .subscribe
                { response ->
                    callback.onResult(response.results(), params.key + 1)
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        //todo
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }

    companion object {
        const val NUMBER_PHOTOS = 30
    }
}