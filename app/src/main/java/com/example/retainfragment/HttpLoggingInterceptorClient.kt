package com.example.retainfragment

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object HttpLoggingInterceptorClient {
    val client: OkHttpClient
        get() = OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()
}