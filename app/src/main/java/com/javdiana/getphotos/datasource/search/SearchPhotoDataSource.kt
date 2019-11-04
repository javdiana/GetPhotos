package com.javdiana.getphotos.datasource.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.javdiana.getphotos.api.Api
import com.javdiana.getphotos.api.api.ApiConstants
import com.javdiana.getphotos.model.Photo
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class SearchPhotoDataSource(
    private val service: Api,
    private val compositeDisposable: CompositeDisposable,
    private val query: MutableLiveData<String>
) : PageKeyedDataSource<Int, Photo>() {
    private var retryCompletable: Completable? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        compositeDisposable.add(
            service.getSearchedPhotos(
                query.value,
                ApiConstants.API_KEY,
                NUMBER_PHOTOS,
                1
            )
                .subscribe({ response ->
                    callback.onResult(response.results(), null, 2)
                }, {
                    setRetry(Action { loadInitial(params, callback) })
                })
        )
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        compositeDisposable.add(
            service.getSearchedPhotos(
                query.value, ApiConstants.API_KEY,
                NUMBER_PHOTOS, params.key
            )
                .subscribe(
                    { response ->
                        callback.onResult(response.results(), params.key + 1)
                    }, {
                        setRetry(Action { loadAfter(params, callback) })
                    })
        )
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

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

    companion object {
        const val NUMBER_PHOTOS = 30
    }
}