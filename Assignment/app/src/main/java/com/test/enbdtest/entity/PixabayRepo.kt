package com.test.enbdtest.entity

data class PixabayRepo(
    var id: Int,
    var tags: String,
    var previewURL: String,
    var previewWidth: Int,
    var previewHeight: Int,
    var webformatURL: String,
    var webformatWidth: Int,
    var webformatHeight: Int,
    var likes: Int,
    var comments: Int)