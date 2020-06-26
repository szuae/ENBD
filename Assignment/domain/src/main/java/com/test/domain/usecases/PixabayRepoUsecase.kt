package com.test.domain.usecases

import com.test.domain.entity.DataEntity
import com.test.domain.entity.RepoEntity
import kotlinx.coroutines.channels.ReceiveChannel

interface PixabayRepoUsecase {
    suspend fun getPixabayRepos(apiKey: String, searchParam: String, pageNo: Int): ReceiveChannel<DataEntity<List<RepoEntity>>>

}