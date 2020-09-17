package com.codingwithnaman.daggerhiltdemo.di

import com.codingwithnaman.daggerhiltdemo.db.CacheMapper
import com.codingwithnaman.daggerhiltdemo.db.PostCacheEntity
import com.codingwithnaman.daggerhiltdemo.db.PostDao
import com.codingwithnaman.daggerhiltdemo.network.NetworkMapper
import com.codingwithnaman.daggerhiltdemo.network.PostApi
import com.codingwithnaman.daggerhiltdemo.network.PostNetworkEntity
import com.codingwithnaman.daggerhiltdemo.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(postApi: PostApi, postDao: PostDao, cacheMapper: CacheMapper, networkMapper: NetworkMapper) : Repository{
        return Repository(postDao, postApi, networkMapper, cacheMapper)
    }
}