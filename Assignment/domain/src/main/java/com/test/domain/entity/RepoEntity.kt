package com.test.domain.entity

data class RepoEntity(
    var id: Int,
    var tags: String,
    var previewURL: String,
    var previewWidth: Int,
    var previewHeight: Int,
    var webformatURL: String,
    var webformatWidth: Int,
    var webformatHeight: Int,
    var likes: Int,
    var comments: Int
)
