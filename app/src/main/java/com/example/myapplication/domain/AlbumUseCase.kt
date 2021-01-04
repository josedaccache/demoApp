package com.example.myapplication.domain

import com.example.myapplication.data.repository.AlbumRepository
import com.example.myapplication.di.DaggerApiComponent
import com.example.myapplication.models.APIResponse
import io.reactivex.Single
import javax.inject.Inject

class AlbumUseCase {

    @Inject
    lateinit var repository: AlbumRepository

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getImages(): Single<APIResponse> {
        return repository.getImages()
    }
}