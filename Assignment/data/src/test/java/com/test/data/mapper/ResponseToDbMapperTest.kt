package com.test.data.mapper

import com.test.data.entity.RepoResponse
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ResponseToDbMapperTest {

    @Mock
    lateinit var response : List<RepoResponse>

    lateinit var entityMapper: ResponseToDbMapper

    @Before
    fun setup() {
        entityMapper = ResponseToDbMapper()
    }
    @Test
    fun mapToEmptyList() {
        response = emptyList()
        assertTrue(entityMapper.map("",response).isEmpty())
    }


    @Test
    fun mapToSuccessCase_dataSize() {
        val list = listOf(RepoResponse(0,"","",0,0,"","",0,0,0,0,0,0,0))
        response = list
        assertTrue(entityMapper.map("",response).isNotEmpty())
    }
}