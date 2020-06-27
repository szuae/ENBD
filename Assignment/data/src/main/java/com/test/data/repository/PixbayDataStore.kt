package com.test.data.repository

import com.test.data.entity.RepoDbEntity
import com.test.domain.entity.DataEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel

interface PixbayDataStore {

    suspend fun getAllPixbay(scope: CoroutineScope,apiKey: String, searchParam: String, pageNo: Int):ReceiveChannel<DataEntity<List<RepoDbEntity>>>

    fun saveAll(response: List<RepoDbEntity>?)

}