package com.apps.elliotgrin.userslist.data.repository

import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.data.remote.Api

class UsersRepository(private val api: Api) {

    suspend fun fetchUsers(): List<User> = api.fetchUsers().await().sortedBy { user -> user.id }

    suspend fun createUser(user: User) = api.createUser(user).await()

    suspend fun updateUser(user: User) = api.updateUser(user.id, user).await()

}