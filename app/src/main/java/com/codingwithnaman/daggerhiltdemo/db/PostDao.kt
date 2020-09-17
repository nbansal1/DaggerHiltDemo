package com.codingwithnaman.daggerhiltdemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postCacheEntity: PostCacheEntity) : Long

    @Query( "SELECT * FROM posts")
    suspend fun getPosts() : List<PostCacheEntity>

}