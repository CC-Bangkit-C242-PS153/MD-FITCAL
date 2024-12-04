package com.example.capstoneproject.data.remote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstoneproject.View.ui.dashboard.CaloriesViewModel
import com.example.capstoneproject.View.ui.physical.PhysicalViewModel
import com.example.capstoneproject.View.ui.sleepcycle.SleepCycleViewModel
import com.example.capstoneproject.data.remote.retrofit.ApiService

class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepCycleViewModel::class.java)) {
            return SleepCycleViewModel(apiService) as T
        }
        if (modelClass.isAssignableFrom(PhysicalViewModel::class.java)) {
            return PhysicalViewModel(apiService) as T
        }
        if (modelClass.isAssignableFrom(CaloriesViewModel::class.java)) {
            return CaloriesViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}