package com.example.capstoneproject.data.remote

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = getFirebaseToken(context)

        if (token != null) {
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "$token")
                .build()
            return chain.proceed(request)
        } else {
            throw IllegalStateException("Token tidak tersedia")
        }
    }
}
