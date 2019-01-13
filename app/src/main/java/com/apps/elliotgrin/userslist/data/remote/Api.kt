package com.apps.elliotgrin.userslist.data.remote

import com.apps.elliotgrin.userslist.data.model.User
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface Api {
    @GET("users.json")
    fun fetchUsers(): Deferred<List<User>>

    @POST("users.json")
    fun createUser(@Body user: User): Deferred<Unit>

    @PATCH("users/{id}.json")
    fun updateUser(@Path("id") id: Int, @Body user: User): Deferred<Unit>
}