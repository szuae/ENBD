package com.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.data.entity.RepoDbEntity

@Database(entities = [RepoDbEntity::class], version = 1)
abstract class PixbayDataBase : RoomDatabase(){
    abstract fun getPixBayRepoDao() : PixBayRepoDao
}