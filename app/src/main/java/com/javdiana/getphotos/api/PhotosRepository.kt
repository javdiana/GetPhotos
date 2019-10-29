package com.javdiana.getphotos.api

import android.content.ContentValues.TAG
import android.util.Log
import com.javdiana.getphotos.model.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rx.Subscription
import java.util.*

class PhotosRepository() {
    private lateinit var photos: List<Photo>
    private lateinit var subscription: Subscription

    companion object {
        val api = GetApiService()
    }

    private fun setPhotos() {
        subscription = api.getPhoto()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Observer<Photo>() {
                fun onCompleted() {
                    Log.d(TAG, "In onCompleted()")
                }

                fun onError(e: Throwable) {
                    Log.d(TAG, "In onError() " + e.message)
                }

                fun onNext(photo: Photo) {
                    photos = listOf(photo)
                    Log.d(TAG, "In onNext()")
                }
            })
    }

    fun getPhotos(): List<Photo> {
        setPhotos()
        return photos;
    }
}