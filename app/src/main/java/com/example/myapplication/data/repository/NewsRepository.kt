package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.source.NewsRemoteSource
import com.example.myapplication.di.DaggerApiComponent
import com.example.myapplication.models.APIResponse
import io.reactivex.Single
import javax.inject.Inject

class NewsRepository {

    @Inject
    lateinit var newsRemoteSource: NewsRemoteSource
    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getNewsArticles () : Single<APIResponse> {
        return newsRemoteSource.getNewsArticles()
    }

}