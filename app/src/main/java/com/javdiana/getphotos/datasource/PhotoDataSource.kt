package com.javdiana.getphotos.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.javdiana.getphotos.model.Photo
import com.javdiana.getphotos.repository.PhotosRepository
import com.javdiana.getphotos.repository.PhotosRepository.Companion.NUMBER_PHOTOS
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

class PhotoDataSource(
    private val repository: PhotosRepository,
    private val compositeDisposable: CompositeDisposable,
    private val query: MutableLiveData<String>,
    private val isSearch: Boolean
) : PageKeyedDataSource<Int, Photo>() {
    private var retryCompletable: Completable? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        compositeDisposable.add(
            if (isSearch) {
                if (query.value != null) {
                    repository.searchPhotos(query.value!!, 1)
                } else {
                    return
                }
                    .subscribe({ response ->
                        callback.onResult(response.results, null, 2)
                    }, { setRetry(Action { loadInitial(params, callback) }) })

            } else {
                repository.getPhotos(1)
                    .subscribe({ response ->
                        callback.onResult(response, null, 2)
                    }, { setRetry(Action { loadInitial(params, callback) }) })
            }
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        compositeDisposable.add(
            if (isSearch) {
                repository.searchPhotos(query.value!!, params.key)
                    .subscribe({ response ->
                        callback.onResult(response.results, params.key + 1)
                    }, { setRetry(Action { loadAfter(params, callback) }) })

            } else {
                repository.getPhotos(params.key)
                    .subscribe({ response ->
                        callback.onResult(response, params.key + 1)
                    }, { setRetry(Action { loadAfter(params, callback) }) })
            }
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        //todo
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}