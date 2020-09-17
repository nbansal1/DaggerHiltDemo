package com.codingwithnaman.daggerhiltdemo.network

import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): List<PostNetworkEntity>
}