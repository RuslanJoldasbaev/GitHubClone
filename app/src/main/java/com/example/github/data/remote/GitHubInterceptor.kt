package com.example.github.data.remote

import com.example.github.data.local.LocalStorage
import okhttp3.Interceptor
import okhttp3.Response

class GitHubInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${LocalStorage().token}").build()
        return chain.proceed(request)
    }
}