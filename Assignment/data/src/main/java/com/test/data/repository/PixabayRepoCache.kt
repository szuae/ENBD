package com.test.data.repository

import com.test.data.db.PixbayDataBase
import com.test.data.entity.RepoDbEntity
import com.test.domain.entity.DataEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.reactive.openSubscription

class PixabayRepoCache(dataBase: PixbayDataBase) : PixbayDataStore {

    private var pixBayRepoDao = dataBase.getPixBayRepoDao()

    override suspend fun getAllPixbay(scope: CoroutineScope,apiKey: String, searchParam: String, pageNo: Int): ReceiveChannel<DataEntity<List<RepoDbEntity>>> {
        val mappedValue = pixBayRepoDao.getAllRecords(searchParam).map {
            DataEntity.SUCCESS(it)
        }
        return mappedValue.openSubscription()
    }

    override fun saveAll(response: List<RepoDbEntity>?) {
        response.let {
            pixBayRepoDao.saveAllRecords(it?: emptyList())
        }
    }
}