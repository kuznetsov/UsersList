package com.apps.elliotgrin.userslist.data.repository

import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.data.remote.Api

class UsersRepository(private val api: Api) {

    suspend fun fetchUsers(): List<User> = api.fetchUsers().await()

}