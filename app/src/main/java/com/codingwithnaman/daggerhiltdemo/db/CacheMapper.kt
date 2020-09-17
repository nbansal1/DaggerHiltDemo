package com.codingwithnaman.daggerhiltdemo.db

import com.codingwithnaman.daggerhiltdemo.model.Post
import com.codingwithnaman.daggerhiltdemo.network.NetworkMapper
import com.codingwithnaman.daggerhiltdemo.util.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper <PostCacheEntity, Post> {
    override fun mapFromEntry(entity: PostCacheEntity): Post {
        return Post(
            entity.userId,
            entity.id,
            entity.title,
            entity.body
        )
    }

    override fun mapToEntry(domainModel: Post): PostCacheEntity {
        return PostCacheEntity(
            domainModel.id,
            domainModel.userId,
            domainModel.title,
            domainModel.body
        )
    }

    fun mapFromEntityList(entities : List<PostCacheEntity>) : List<Post> {
        return entities.map {
            mapFromEntry(it)
        }
    }
}