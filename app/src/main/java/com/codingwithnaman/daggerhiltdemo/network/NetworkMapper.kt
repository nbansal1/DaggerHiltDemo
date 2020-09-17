package com.codingwithnaman.daggerhiltdemo.network

import com.codingwithnaman.daggerhiltdemo.model.Post
import com.codingwithnaman.daggerhiltdemo.util.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<PostNetworkEntity, Post>{
    override fun mapFromEntry(entity: PostNetworkEntity): Post {
        return Post(
            entity.userId,
            entity.id,
            entity.title,
            entity.body
        )
    }

    override fun mapToEntry(domainModel: Post): PostNetworkEntity {
        return PostNetworkEntity(
            domainModel.body,
            domainModel.id,
            domainModel.title,
            domainModel.userId
        )
    }

    fun mapFromEntityList(entities: List<PostNetworkEntity>) : List<Post> {
        return entities.map {
            mapFromEntry(it)
        }
    }

}