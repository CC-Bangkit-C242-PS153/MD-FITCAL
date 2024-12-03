package com.example.capstoneproject.View.ui.sleepcycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.FitcalRepository
import com.example.capstoneproject.data.remote.modelRequest.SleepRequest
import com.example.capstoneproject.data.remote.response.FitcalResponse
import com.example.capstoneproject.data.remote.retrofit.ApiService


class SleepCycleViewModel(private val apiService: ApiService) : ViewModel() {

    fun predictSleep(sleepRequest: SleepRequest): LiveData<FitcalResponse?> {
        return FitcalRepository(apiService).predictSleep(sleepRequest)
    }
}
