package com.example.myapplication.di

import com.example.myapplication.data.remote.source.AlbumRemoteSource
import com.example.myapplication.data.remote.source.ContactUsRemoteSource
import com.example.myapplication.data.remote.source.NewsRemoteSource
import com.example.myapplication.data.repository.AlbumRepository
import com.example.myapplication.data.repository.ContactUsRepository
import com.example.myapplication.data.repository.NewsRepository
import com.example.myapplication.domain.AlbumUseCase
import com.example.myapplication.domain.ContactUsUseCase
import com.example.myapplication.domain.NewsUseCase
import com.example.myapplication.presentation.viewmodel.AlbumViewModel
import com.example.myapplication.presentation.viewmodel.ContactUsViewModel
import com.example.myapplication.presentation.viewmodel.NewsViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(remoteSource: NewsRemoteSource)

    fun inject(repository: NewsRepository)

    fun inject(newsUseCase: NewsUseCase)

    fun inject(viewModel: NewsViewModel)

    fun inject(remoteSource: ContactUsRemoteSource)

    fun inject(repository: ContactUsRepository)

    fun inject(contactUsUseCase: ContactUsUseCase)

    fun inject(viewModel: ContactUsViewModel)

    fun inject(remoteSource: AlbumRemoteSource)

    fun inject(repository: AlbumRepository)

    fun inject(contactUsUseCase: AlbumUseCase)

    fun inject(viewModel: AlbumViewModel)


}