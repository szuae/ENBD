package com.test.data.mapper

import com.test.data.entity.RepoResponse
import com.test.domain.entity.RepoEntity

class ResponseDataToDomainEntityMapper {
    fun mapTo(response: List<RepoResponse>?): List<RepoEntity> = mapToList(response)

    private fun mapToList(responses: List<RepoResponse>?)
            : List<RepoEntity> = responses?.map { mapToDomainEntity(it) } ?: emptyList()

    private fun mapToDomainEntity(response: RepoResponse): RepoEntity = RepoEntity(
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