package com.example.projeto1alexandre.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")  val avatarUrl: String
)