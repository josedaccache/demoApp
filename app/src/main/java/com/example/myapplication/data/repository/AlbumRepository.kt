package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.source.AlbumRemoteSource
import com.example.myapplication.di.DaggerApiComponent
import com.example.myapplication.models.APIResponse
import io.reactivex.Single
import javax.inject.Inject

class AlbumRepository {

    @Inject
    lateinit var remoteSource: AlbumRemoteSource

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getImages(): Single<APIResponse> {
        return remoteSource.getImages()
    }
}