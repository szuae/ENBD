package com.test.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PixbayResponseTable")
data class RepoDbEntity(
    @PrimaryKey var id: Int = 0,
    var tags: String,
    var previewURL: String,
    var previewWidth: Int,
    var previewHeight: Int,
    var webformatURL: String,
    var largeImageURL: String,
    var webformatWidth: Int,
    var webformatHeight: Int,
    var views: Int,
    var downloads: Int,
    var favorites: Int,
    var likes: Int,
    var comments: Int)