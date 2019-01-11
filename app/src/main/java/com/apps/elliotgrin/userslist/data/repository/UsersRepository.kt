package com.apps.elliotgrin.userslist.data.repository

import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.data.remote.Api
import kotlinx.coroutines.Deferred

class UsersRepository(val api: Api) {
    suspend fun fetchUsers(): Deferred<List<User>> = api.fetchUsers()
}