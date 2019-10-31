package com.javdiana.getphotos.view.listphotos

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
import rx.Subscription


class ListPhotosViewModel() : BaseViewModel() {

    var photos = MutableLiveData<ArrayList<Photo>>()
    private lateinit var subscription: Subscription


    private fun getObserver(): Observer<ArrayList<Photo>> {
        return object : io.reactivex.Observer<ArrayList<Photo>> {
            override fun onNext(photo: ArrayList<Photo>) {
                Log.d(TAG, "onNext: $photo")
                photos.postValue(photo)
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
        val myObservable = getObservable()

        val myObserver = getObserver()

        myObservable?.subscribeOn(Schedulers.newThread())?.subscribe(myObserver)
    }

    private fun getObservable(): Observable<ArrayList<Photo>>? {
        return PhotosService.getInstance().getApi().getPhotos(Utils.API_KEY)
    }

    companion object {
        const val TAG = "Tag"
    }
}