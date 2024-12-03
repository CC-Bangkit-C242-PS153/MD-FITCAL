package com.example.capstoneproject.View.ui.dashboard

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.FitcalRepository
import com.example.capstoneproject.data.remote.modelRequest.CaloriesRequest
import com.example.capstoneproject.data.remote.response.FitcalResponse
import com.example.capstoneproject.data.remote.retrofit.ApiService
import okhttp3.MultipartBody


class CaloriesViewModel(private val apiService: ApiService) : ViewModel() {
    fun predictCalories(   photo: MultipartBody.Part,  water: Number,
                           protein: Number,
                           lipid: Number,
                           ash: Number,
                           carbohydrate:Number,
                           fiber: Number,
                           sugar: Number,):LiveData<FitcalResponse?>{
        return FitcalRepository(apiService).predictCalories(photo, water,protein,lipid, ash, carbohydrate, fiber, sugar)
    }



}