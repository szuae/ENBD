package com.test.data.entity

import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("tags") var tags: String,
    @SerializedName("previewURL") var previewURL: String,
    @SerializedName("previewWidth") var previewWidth: Int,
    @SerializedName("previewHeight") var previewHeight: Int,
    @SerializedName("webformatURL") var webformatURL: String,
    @SerializedName("largeImageURL") var largeImageURL: String,
    @SerializedName("webformatWidth") var webformatWidth: Int,
    @SerializedName("webformatHeight") var webformatHeight: Int,
    @SerializedName("views") var views: Int,
    @SerializedName("downloads") var downloads: Int,
    @SerializedName("favorites") var favorites: Int,
    @SerializedName("likes") var likes: Int,
    @SerializedName("comments") var comments: Int)
