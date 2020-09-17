package com.codingwithnaman.daggerhiltdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostCacheEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun getPostDao() : PostDao

    companion object{
        val TABLE_NAME : String = "posts.db"
    }
}