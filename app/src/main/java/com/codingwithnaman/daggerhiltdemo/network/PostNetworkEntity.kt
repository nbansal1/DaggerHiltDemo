package com.codingwithnaman.daggerhiltdemo.network


import com.google.gson.annotations.SerializedName

data class PostNetworkEntity(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)