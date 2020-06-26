package com.test.data.api

import com.test.data.entity.RepoResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayRepoApi {
    @GET("/api/")
    fun getPixabayRepos(@Query("key")  apiKey: String,
                        @Query("q") searchParam: String,
                        @Query("per_page") itemPerPage: Int,
                        @Query("page") pageNo: Int): Deferred<List<RepoResponse>>
}