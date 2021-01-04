package com.example.myapplication.di

import com.example.myapplication.data.remote.service.APIService
import com.example.myapplication.data.remote.source.AlbumRemoteSource
import com.example.myapplication.data.remote.source.ContactUsRemoteSource
import com.example.myapplication.data.remote.source.NewsRemoteSource
import com.example.myapplication.data.repository.AlbumRepository
import com.example.myapplication.data.repository.ContactUsRepository
import com.example.myapplication.data.repository.NewsRepository
import com.example.myapplication.domain.AlbumUseCase
import com.example.myapplication.domain.ContactUsUseCase
import com.example.myapplication.domain.NewsUseCase
import com.example.myapplication.utils.GlobalVars.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideAPIService(): APIService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    @Provides
    fun provideNewsModule(): NewsRemoteSource {
        return NewsRemoteSource()
    }

    @Provides
    fun provideNewsRepository(): NewsRepository {
        return NewsRepository()
    }

    @Provides
    fun provideNewsUserCase(): NewsUseCase {
        return NewsUseCase()
    }

    @Provides
    fun provideContactUsModule(): ContactUsRemoteSource {
        return ContactUsRemoteSource()
    }

    @Provides
    fun provideContactUsRepository(): ContactUsRepository {
        return ContactUsRepository()
    }

    @Provides
    fun provideContactUsUseCase(): ContactUsUseCase {
        return ContactUsUseCase()
    }

    @Provides
    fun provideAlbumModule(): AlbumRemoteSource {
        return AlbumRemoteSource()
    }

    @Provides
    fun provideAlbumRepository(): AlbumRepository {
        return AlbumRepository()
    }

    @Provides
    fun provideAlbumUseCase(): AlbumUseCase {
        return AlbumUseCase()
    }

}