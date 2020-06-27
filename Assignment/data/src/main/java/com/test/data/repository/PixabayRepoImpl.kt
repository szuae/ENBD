package com.test.data.repository

import com.test.data.BuildConfig
import com.test.data.api.PixabayRepoApi
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
                            cache.saveAll(pixbayResponse.data)
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
}