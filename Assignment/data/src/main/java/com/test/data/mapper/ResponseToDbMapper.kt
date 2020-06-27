package com.test.data.mapper

import com.test.data.entity.RepoDbEntity
import com.test.data.entity.RepoResponse
import com.test.domain.entity.RepoEntity

class ResponseToDbMapper {
    fun map(response: List<RepoResponse>?) :List<RepoDbEntity>  =mapToDbEntityList(response)

    private fun mapToDbEntityList(responses: List<RepoResponse>?)
            : List<RepoDbEntity> = responses?.map { mapToDbEntity(it) } ?: emptyList()

    private fun mapToDbEntity(response: RepoResponse): RepoDbEntity = RepoDbEntity(
        id = response.id,
        tags = response.tags,
        previewURL = response.previewURL,
        previewWidth = response.previewWidth,
        previewHeight = response.previewHeight,
        webformatURL = response.webformatURL,
        webformatWidth = response.webformatWidth,
        webformatHeight = response.webformatHeight,
        comments = response.comments,
        views = response.views,
        downloads = response.downloads,
        favorites = response.favorites,
        likes = response.likes,
        largeImageURL = response.largeImageURL)
}