package com.example.capstoneproject.View.ui.physical

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.FitcalRepository
import com.example.capstoneproject.data.remote.modelRequest.PhysicalRequest
import com.example.capstoneproject.data.remote.modelRequest.SleepRequest
import com.example.capstoneproject.data.remote.response.FitcalResponse
import com.example.capstoneproject.data.remote.retrofit.ApiService

class PhysicalViewModel(private val apiService: ApiService) : ViewModel() {

    fun predictPhysical(physicalRequest: PhysicalRequest): LiveData<FitcalResponse?> {
        return FitcalRepository(apiService).predictPhysical(physicalRequest)
    }
}