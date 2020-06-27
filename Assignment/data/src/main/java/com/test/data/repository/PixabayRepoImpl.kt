package com.test.data.repository

import android.util.Log
import com.test.data.BuildConfig
import com.test.data.api.PixabayRepoApi
import com.test.data.entity.RepoDbEntity
import com.test.data.mapper.ReponseToDomainMapper
import com.test.data.mapper.ResponseToDbMapper
import com.test.data.util.NetworkUtil
import com.test.domain.entity.DataEntity
import com.test.domain.entity.RepoEntity
import com.test.domain.repository.PixabayRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch

class PixabayRepoImpl(private val remote: PixabayRepoRemote,
                      private val cache: PixabayRepoCache ,private val networkState : NetworkUtil) : PixabayRepository {

    private var mapper = ReponseToDomainMapper()

    override suspend fun getPixabayRepos(scope: CoroutineScope, apiKey: String, searchParam: String, pageNo: Int): ReceiveChannel<DataEntity<List<RepoEntity>>> {
        return scope.produce {

            if(!networkState.isInternetAvailable()) {
                launch {
                    val pixbayResponse = mapper.mapDbToDomain(cache.getAllPixbay(scope,apiKey, searchParam, pageNo).receive())
                    send(DataEntity.SUCCESS(pixbayResponse))
                }
            }else {
                launch {
                    when (val pixbayResponse = remote.getAllPixbay(scope,apiKey, searchParam, pageNo).receive()) {
                        is DataEntity.SUCCESS -> {

                            cache.saveAll(filterData(pixbayResponse.data))
                            send(DataEntity.SUCCESS(mapper.mapDbToDomain(pixbayResponse)))
                        }
                        is DataEntity.ERROR -> {
                            send(DataEntity.ERROR("" + pixbayResponse.error))
                        }
                    }
                }
            }
        }
    }

    private fun filterData(data: List<RepoDbEntity>?) : List<RepoDbEntity>{
        var filteredList = ArrayList<RepoDbEntity>()
        data?.listIterator()?.forEach {
            if(it.tags.filter { it == ',' }.count() <3)
                filteredList.add(it)
        }
        return filteredList
    }
}