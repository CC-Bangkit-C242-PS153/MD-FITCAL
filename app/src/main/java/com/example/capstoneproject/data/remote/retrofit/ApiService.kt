package com.example.capstoneproject.data.remote.retrofit


import androidx.lifecycle.LiveData
import com.example.capstoneproject.data.remote.modelRequest.PhysicalRequest
import com.example.capstoneproject.data.remote.modelRequest.SleepRequest

import com.example.capstoneproject.data.remote.response.FitcalResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiService {
    @Multipart
    @POST("inferences/calories")
    fun predictCalories(
        @Part image: MultipartBody.Part,
        @Part("water") water : RequestBody,
        @Part("protein") protein : RequestBody,
        @Part("lipid") lipid : RequestBody,
        @Part("ash") ash : RequestBody,
        @Part("carbohydrate") carbohydrate : RequestBody,
        @Part("fiber") fiber: RequestBody,
        @Part("sugar") sugar : RequestBody,
    ): Call<FitcalResponse>


    @POST("inferences/sleep")
    fun predectedSleep(
        @Body requestBody: SleepRequest
    ): Call<FitcalResponse>

    @POST("inferences/physical")
    fun predectedPhysical(
        @Body requestBody: PhysicalRequest
    ) : Call<FitcalResponse>
}