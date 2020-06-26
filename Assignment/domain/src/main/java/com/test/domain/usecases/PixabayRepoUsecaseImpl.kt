package com.test.domain.usecases

import com.test.domain.entity.DataEntity
import com.test.domain.entity.RepoEntity
import com.test.domain.repository.PixabayRepository
import kotlinx.coroutines.channels.ReceiveChannel


class PixabayRepoUsecaseImpl(private val PixabayRepository: PixabayRepository): PixabayRepoUsecase {
    override suspend fun getPixabayRepos(apiKey: String, searchParam: String, pageNo: Int): ReceiveChannel<DataEntity<List<RepoEntity>>> {
        return PixabayRepository.getPixabayRepos(apiKey, searchParam, pageNo)
    }
}
