package com.test.data.repository

import com.test.data.BuildConfig
import com.test.data.api.PixabayRepoApi
import com.test.data.mapper.ResponseDataToDomainEntityMapper
import com.test.domain.entity.DataEntity
import com.test.domain.entity.RepoEntity
import com.test.domain.repository.PixabayRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class PixabayRepoImpl(private val api: PixabayRepoApi) : PixabayRepository {

    private var mapper = ResponseDataToDomainEntityMapper()

    override suspend fun getPixabayRepos(searchParam: String, pageNo: Int): ReceiveChannel<DataEntity<List<RepoEntity>>> {
        return GlobalScope.produce {
            try {
                val repoResponse = api.getPixabayRepos(BuildConfig.API_KEY,searchParam,BuildConfig.ITEM_PER_PAGE.toInt(),pageNo).await()
                send(DataEntity.SUCCESS(mapper.mapTo(repoResponse)))
            } catch (e: Exception) {
                send(DataEntity.ERROR(""+e.message))
            }
        }
    }
}