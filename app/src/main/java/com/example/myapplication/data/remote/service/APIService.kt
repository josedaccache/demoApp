package com.example.myapplication.data.remote.service

import com.example.myapplication.models.APIResponse
import com.example.myapplication.utils.GlobalVars.GET_FAILURE_CONTACT_API
import com.example.myapplication.utils.GlobalVars.GET_IMAGES
import com.example.myapplication.utils.GlobalVars.GET_NEWS_API
import com.example.myapplication.utils.GlobalVars.GET_SUCCESS_CONTACT_API
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIService {


    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET(GET_NEWS_API)
    fun getNewsArticles(): Single<APIResponse>

    @GET(GET_SUCCESS_CONTACT_API)
    fun getSuccessContactUs() : Single<APIResponse>

    @GET(GET_FAILURE_CONTACT_API)
    fun getFailureContactUs() : Single<APIResponse>

    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET(GET_IMAGES)
    fun getImages(): Single<APIResponse>
}