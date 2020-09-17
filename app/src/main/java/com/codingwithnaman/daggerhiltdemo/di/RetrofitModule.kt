package com.codingwithnaman.daggerhiltdemo.di

import com.codingwithnaman.daggerhiltdemo.network.PostApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providesGson() : Gson{
        return Gson()
    }

    @Singleton
    @Provides
    fun getRetrofit(gson: Gson) : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providePostService(retrofit: Retrofit) : PostApi{
        return retrofit.create(PostApi::class.java)
    }


}