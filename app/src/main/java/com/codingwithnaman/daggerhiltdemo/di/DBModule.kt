package com.codingwithnaman.daggerhiltdemo.di

import android.content.Context
import androidx.room.Room
import com.codingwithnaman.daggerhiltdemo.db.PostDao
import com.codingwithnaman.daggerhiltdemo.db.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DBModule {

    @Singleton
    @Provides
    fun providePostDb(@ApplicationContext context: Context) : PostDatabase{
        return Room.databaseBuilder(
            context,
            PostDatabase::class.java,
            PostDatabase.TABLE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providePostDao(postDatabase: PostDatabase) : PostDao{
        return postDatabase.getPostDao()
    }
}