package com.test.domain.entity

data class RepoEntity(
    var id: Int = 0,
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
    var comments: Int
)