package com.javdiana.getphotos.view.listphotos

import androidx.lifecycle.MutableLiveData
import com.javdiana.getphotos.api.PhotosRepository
import com.javdiana.getphotos.model.Photo
import com.javdiana.mynotes.ui.base.BaseViewModel
import io.reactivex.schedulers.Schedulers

class ListPhotosViewModel(private val photosRepository: PhotosRepository) : BaseViewModel() {

    var photos = MutableLiveData<ArrayList<Photo>>()
    fun getPhotos() {
        addDisposible(
            photosRepository.getPhotos().subscribeOn(Schedulers.newThread()).subscribe({ list ->
                photos.postValue(ArrayList(list))
            }, {}))
    }
}