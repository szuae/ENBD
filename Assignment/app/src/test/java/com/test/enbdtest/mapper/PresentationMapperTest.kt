package com.test.enbdtest.mapper

import android.provider.ContactsContract
import com.test.domain.entity.DataEntity
import com.test.domain.entity.RepoEntity
import com.test.enbdtest.entity.Data
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PresentationMapperTest {

    var data : DataEntity<List<RepoEntity>> = DataEntity.SUCCESS(emptyList())

    lateinit var  presenter : PresentationMapper

    @Before
    fun setup() {
        presenter = PresentationMapper()
    }

    @Test
    fun mapToSuccessCase_ObjectNotNull() {
        var result = presenter.mapTo(data)
        assertNotNull(result)
    }

    @Test
    fun mapToSuccessCase_dataSize() {
        val list = listOf(RepoEntity(0,"","",0,0,"","",0,0,0,0,0,0,0))
        data = DataEntity.SUCCESS(list)
        var result = presenter.mapTo(data)
        assertFalse((result as Data.SUCCESS).data!!.isEmpty())
    }


    @Test
    fun mapToSuccessCase_Data_Status() {
        var result = presenter.mapTo(data)
        assertTrue((result as Data.SUCCESS).data!!.isEmpty())
    }

    @Test
    fun mapToFailedCase() {
        var result = presenter.mapTo(data = DataEntity.ERROR("error"))
        assertTrue((result as Data.ERROR).error == "error")
    }
}
