package com.example.myapplication.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class APIResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: ArrayList<Article>?,
    @SerializedName("imageData")
    val imageData: ArrayList<Image>?
) : Serializable

data class Article(
    @SerializedName("source")
    val source: Source?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("content")
    val content: String?
) : Serializable

data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("city")
    val name: String?
) : Serializable

data class Image(
    @SerializedName("source")
    val source: String
): Serializable
