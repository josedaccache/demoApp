package com.example.myapplication.interfaces

import com.example.myapplication.models.Article

interface NewsClickListener {
    fun onNewsClicked(article: Article)
}