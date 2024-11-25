package com.example.otchallenge.domain.model

import com.google.gson.annotations.SerializedName

data class BookModel(
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: String,
    @SerializedName("title") val title: String,
    @SerializedName("author") val author: String,
    @SerializedName("book_image") val bookImage: String,
    @SerializedName("book_image_width") val bookImageWidth: Int,
    @SerializedName("book_image_height") val bookImageHeight: Int,
)