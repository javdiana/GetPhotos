package com.javdiana.getphotos.view.searchphotos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.javdiana.getphotos.api.service.PhotosService
import com.javdiana.getphotos.api.service.Utils
import com.javdiana.getphotos.model.Photo
import com.javdiana.getphotos.view.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchListPhotosViewModel() : BaseViewModel() {
    var searchPhotos = MutableLiveData<ArrayList<Photo>>()
    var query: String = "office"

    private fun getObserver(): Observer<ArrayList<Photo>> {
        return object : Observer<ArrayList<Photo>> {
            override fun onNext(photo: ArrayList<Photo>) {
                Log.d(TAG, "onNext: $photo")
                searchPhotos.postValue(photo)
            }

            override fun onSubscribe(d: Disposable) {
                addDisposable(d)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError: ${e.message}")
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }
        }
    }

    fun getPhotos() {
        val myObservable = getObservablePhotos()

        val myObserver = getObserver()

        myObservable?.subscribeOn(Schedulers.newThread())?.subscribe(myObserver)
    }

    private fun getObservablePhotos(): Observable<ArrayList<Photo>>? {
        return PhotosService.getInstance().getApi()
                .getSearchedPhotos(query, Utils.API_KEY, NUMBER_PHOTOS)
    }

    companion object {
        const val TAG = "Tag"
        const val NUMBER_PHOTOS = 30
    }
}