package com.test.data.mapper

import com.test.data.entity.RepoDbEntity
import com.test.data.entity.RepoResponse
import com.test.domain.entity.DataEntity
import com.test.domain.entity.RepoEntity

class ReponseToDomainMapper {

    fun mapTo(response: List<RepoResponse>?): List<RepoEntity> = response?.map { mapToDomainEntity(it) } ?: emptyList()

    private fun mapToDomainEntity(response: RepoResponse): RepoEntity = RepoEntity(
        id = response.id,
        tags = response.tags,
        previewURL = response.previewURL,
        previewWidth = response.previewWidth,
        previewHeight = response.previewHeight,
        webformatURL = response.webformatURL,
        webformatWidth = response.webformatWidth,
        webformatHeight = response.webformatHeight,
        comments = response.comments,
        largeImageURL = response.largeImageURL,
        views = response.views,
        downloads = response.downloads,
        favorites = response.favorites,
        likes = response.likes)


    fun mapDbToDomain(response: DataEntity<List<RepoDbEntity>>): List<RepoEntity>? {
        return when (response) {
            is DataEntity.SUCCESS<List<RepoDbEntity>> ->
                response.data?.map { mapToDomainEntity(it) }
            is DataEntity.ERROR<List<RepoDbEntity>> ->
                response.data?.map { mapToDomainEntity(it) }
        }
    }

    private fun mapToDomainEntity(response: RepoDbEntity): RepoEntity = RepoEntity(
        id = response.id,
        tags = response.tags,
        previewURL = response.previewURL,
        previewWidth = response.previewWidth,
        previewHeight = response.previewHeight,
        webformatURL = response.webformatURL,
        webformatWidth = response.webformatWidth,
        webformatHeight = response.webformatHeight,
        largeImageURL = response.largeImageURL,
        comments = response.comments,
        views = response.views,
        downloads = response.downloads,
        favorites = response.favorites,
        likes = response.likes)
}