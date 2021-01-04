package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.di.DaggerApiComponent
import com.example.myapplication.domain.NewsUseCase
import com.example.myapplication.models.APIResponse
import com.example.myapplication.models.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel : ViewModel() {

    @Inject
    lateinit var userCase: NewsUseCase

    private val disposable = CompositeDisposable()

    val response = MutableLiveData<APIResponse>()
    val responseLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val selectedArticle = MutableLiveData<Article>()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetchNews() {
        loading.value = true
        disposable.add(
            userCase.getNewsArticles()
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