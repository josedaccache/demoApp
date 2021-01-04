package com.example.myapplication.data.remote.source

import com.example.myapplication.data.remote.service.APIService
import com.example.myapplication.di.DaggerApiComponent
import com.example.myapplication.models.APIResponse
import io.reactivex.Single
import javax.inject.Inject

class NewsRemoteSource {
    @Inject
    lateinit var service: APIService

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getNewsArticles(): Single<APIResponse> {
        return service.getNewsArticles()
    }

}