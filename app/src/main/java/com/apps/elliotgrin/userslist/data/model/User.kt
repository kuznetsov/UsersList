package com.apps.elliotgrin.userslist.data.model

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatarUrl: String?,
    val createdAt: String,
    val updatedAt: String,
    val url: String
)