package com.test.domain.repository

import com.test.domain.entity.DataEntity
import com.test.domain.entity.RepoEntity
import kotlinx.coroutines.channels.ReceiveChannel

interface PixabayRepository {
    suspend fun getPixabayRepos(searchParam: String, pageNo: Int): ReceiveChannel<DataEntity<List<RepoEntity>>>

}