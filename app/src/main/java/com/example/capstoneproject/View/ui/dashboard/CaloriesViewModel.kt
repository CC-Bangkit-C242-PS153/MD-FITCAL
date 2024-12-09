package com.example.capstoneproject.View.ui.dashboard

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.FitcalRepository
import com.example.capstoneproject.data.remote.response.FitcalResponse
import com.example.capstoneproject.data.remote.retrofit.ApiService
import java.io.File


class CaloriesViewModel(private val apiService: ApiService) : ViewModel() {
    fun predictCalories(   photo: File,  water: Number,
                           protein: Number,
                           lipid: Number,
                           ash: Number,
                           carbohydrate:Number,
                           fiber: Number,
                           sugar: Number,):LiveData<FitcalResponse?>{
        return FitcalRepository(apiService).predictCalories(photo, water,protein,lipid, ash, carbohydrate, fiber, sugar)
    }



}