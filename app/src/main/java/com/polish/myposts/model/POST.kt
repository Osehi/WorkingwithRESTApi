package com.polish.myposts.model


import com.google.gson.annotations.SerializedName

data class POST(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)