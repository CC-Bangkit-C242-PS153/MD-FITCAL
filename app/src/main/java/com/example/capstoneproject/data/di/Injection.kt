package com.example.capstoneproject.data.di

import android.content.Context
import com.example.capstoneproject.data.FitcalRepository
import com.example.capstoneproject.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context, token: String): FitcalRepository {
        val apiService = ApiConfig.getApiService(context)
        return FitcalRepository(apiService)
    }
}