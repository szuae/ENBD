package com.test.data.api

import com.test.data.entity.RepoResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface PixabayRepoApi {
    @GET("/api")
    fun getPixabayRepos(): Deferred<List<RepoResponse>>
}