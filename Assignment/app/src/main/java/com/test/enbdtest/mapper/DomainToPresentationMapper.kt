package com.test.enbdtest.mapper

import com.test.domain.entity.DataEntity
import com.test.domain.entity.RepoEntity
import com.test.enbdtest.entity.Data
import com.test.enbdtest.entity.PixabayRepo

class DomainToPresentationMapper {
    fun mapTo(data: DataEntity<List<RepoEntity>>): Data<List<PixabayRepo>> {
        return when (data) {
            is DataEntity.SUCCESS -> Data.SUCCESS(data.data?.let { mapToList(it) })
            is DataEntity.ERROR -> Data.ERROR(error = data.error)
        }
    }

    private fun mapToList(responses: List<RepoEntity>?)
            : List<PixabayRepo> = responses?.map { mapToPresentationEntity(it) } ?: emptyList()

    private fun mapToPresentationEntity(response: RepoEntity): PixabayRepo = PixabayRepo(
        id = response.id,
        tags = response.tags,
        previewURL = response.previewURL,
        previewWidth = response.previewWidth,
        previewHeight = response.previewHeight,
        webformatURL = response.webformatURL,
        webformatWidth = response.webformatWidth,
        webformatHeight = response.webformatHeight,
        likes = response.likes,
        comments = response.comments
    )
}