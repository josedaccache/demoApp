package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.di.DaggerApiComponent
import com.example.myapplication.domain.AlbumUseCase
import com.example.myapplication.models.APIResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumViewModel : ViewModel() {

    @Inject
    lateinit var albumUserCase: AlbumUseCase

    private val disposable = CompositeDisposable()

    val response = MutableLiveData<APIResponse>()
    val responseLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetchImage(){
        loading.value = true
        disposable.add(
            albumUserCase.getImages()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<APIResponse>() {
                    override fun onSuccess(value: APIResponse?) {
                        response.value = value
                        responseLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        responseLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}