package com.javdiana.mynotes.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel(){
    private val compositeDisposable = CompositeDisposable()


    fun addDisposible(disposable: Disposable){
        compositeDisposable.add(disposable)
    }
}