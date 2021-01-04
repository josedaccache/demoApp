package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.source.ContactUsRemoteSource
import com.example.myapplication.di.DaggerApiComponent
import com.example.myapplication.models.APIResponse
import io.reactivex.Single
import javax.inject.Inject

class ContactUsRepository {

    @Inject
    lateinit var remoteSource: ContactUsRemoteSource

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getSuccessContactUs(): Single<APIResponse> {
        return remoteSource.getSuccessContactUs()
    }

    fun getFailureContactUs(): Single<APIResponse> {
        return remoteSource.getFailureContactUs()
    }

}