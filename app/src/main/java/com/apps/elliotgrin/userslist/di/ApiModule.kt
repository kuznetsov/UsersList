package com.apps.elliotgrin.userslist.di

import com.apps.elliotgrin.userslist.data.remote.Api
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://bb-test-server.herokuapp.com/"

val apiModule = module {
    single { createHttpClient() }
    single { createConverter() }
    single { createRetrofit(get(), get(), BASE_URL) }
    single { getApi<Api>(get()) }
}

fun createHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()

    // timeout settings
    builder.connectTimeout(60, TimeUnit.SECONDS)
    builder.readTimeout(60, TimeUnit.SECONDS)
    builder.writeTimeout(60, TimeUnit.SECONDS)
    return builder.build()
}

fun createConverter(): Converter.Factory {
    val builder = GsonBuilder()
    builder.serializeNulls()
    builder.setLenient()
    builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    return GsonConverterFactory.create(builder.create())
}

fun createRetrofit(client: OkHttpClient, converter: Converter.Factory, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(converter)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

inline fun <reified T> getApi(retrofit: Retrofit): T = retrofit.create(T::class.java)