package com.test.data.repository

import com.test.data.BuildConfig
import com.test.data.api.PixabayRepoApi
import com.test.data.entity.RepoDbEntity
import com.test.data.mapper.ResponseToDbMapper
import com.test.domain.entity.DataEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class PixabayRepoRemote(private val api: PixabayRepoApi) : PixbayDataStore {

    private var mapper =ResponseToDbMapper()

    override fun saveAll(response: List<RepoDbEntity>?) {}

    override suspend fun getAllPixbay(scope: CoroutineScope,apiKey: String, searchParam: String, pageNo: Int): ReceiveChannel<DataEntity<List<RepoDbEntity>>> {
        return scope.produce {
            try {
                val repoResponse =  api.getPixabayRepos(apiKey,searchParam,
                    BuildConfig.ITEM_PER_PAGE.toInt(),pageNo).await()
                send(DataEntity.SUCCESS(mapper.map(searchParam,repoResponse.hits)))
            } catch (e: Exception) {
                send(DataEntity.ERROR(""+e.message))
            }
        }
    }
}