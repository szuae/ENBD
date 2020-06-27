package com.test.data.entity

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("total") var total: Int,
    @SerializedName("totalHits") var totalHits: Int,
    @SerializedName("hits") var hits: List<RepoResponse>)

