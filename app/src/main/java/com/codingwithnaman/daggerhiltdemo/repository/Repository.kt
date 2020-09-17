package com.codingwithnaman.daggerhiltdemo.repository

import com.codingwithnaman.daggerhiltdemo.db.CacheMapper
import com.codingwithnaman.daggerhiltdemo.db.PostCacheEntity
import com.codingwithnaman.daggerhiltdemo.db.PostDao
import com.codingwithnaman.daggerhiltdemo.model.Post
import com.codingwithnaman.daggerhiltdemo.network.NetworkMapper
import com.codingwithnaman.daggerhiltdemo.network.PostApi
import com.codingwithnaman.daggerhiltdemo.network.PostNetworkEntity
import com.codingwithnaman.daggerhiltdemo.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class Repository constructor(
    private val postDao: PostDao,
    private val postApi: PostApi,
    private val networkMapper: NetworkMapper,
    private val cacheMapper: CacheMapper
) {

    suspend fun getPosts() : Flow<DataState<List<Post>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkPost = postApi.getPosts()
            val posts =  networkMapper.mapFromEntityList(networkPost)
            for (post in posts) {
                postDao.insertPost(cacheMapper.mapToEntry(post))
            }
            val cachePost = postDao.getPosts()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachePost)))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}