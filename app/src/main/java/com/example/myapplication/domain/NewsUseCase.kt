package com.example.myapplication.domain

import com.example.myapplication.data.repository.NewsRepository
import com.example.myapplication.di.DaggerApiComponent
import com.example.myapplication.models.APIResponse
import io.reactivex.Single
import javax.inject.Inject

class NewsUseCase {

    @Inject
    lateinit var repository: NewsRepository

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getNewsArticles(): Single<APIResponse> {
        return repository.getNewsArticles()
    }
}