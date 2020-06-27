package com.test.data.db

import androidx.room.*
import com.test.data.entity.RepoDbEntity
import io.reactivex.Flowable

@Dao
interface PixBayRepoDao {

    @Query("Select * from PixbayResponseTable where searchParam = :searchParam")
    fun getAllRecords(searchParam: String): Flowable<List<RepoDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllRecords(treadingRepoList: List<RepoDbEntity>) : List<Long>

    @Delete
    fun delete(response: RepoDbEntity)

    @Query("Delete from PixbayResponseTable")
    fun deleteAll()
}
