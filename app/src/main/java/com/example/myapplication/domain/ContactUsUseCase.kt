package com.example.myapplication.domain

import com.example.myapplication.data.repository.ContactUsRepository
import com.example.myapplication.di.DaggerApiComponent
import com.example.myapplication.models.APIResponse
import io.reactivex.Single
import javax.inject.Inject

class ContactUsUseCase {

    @Inject
    lateinit var repository: ContactUsRepository

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getContactUs(isSuccess: Boolean): Single<APIResponse> {
        return if (isSuccess)
            repository.getSuccessContactUs()
        else
            repository.getFailureContactUs()
    }

}