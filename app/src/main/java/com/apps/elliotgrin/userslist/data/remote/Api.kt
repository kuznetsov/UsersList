package com.apps.elliotgrin.userslist.data.remote

import com.apps.elliotgrin.userslist.data.model.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {
    @GET("users.json")
    fun fetchUsers(): Deferred<List<User>>
}