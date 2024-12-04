package com.example.capstoneproject.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstoneproject.data.remote.modelRequest.PhysicalRequest
import com.example.capstoneproject.data.remote.modelRequest.SleepRequest
import com.example.capstoneproject.data.remote.response.FitcalResponse
import com.example.capstoneproject.data.remote.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback // Import Callback dari retrofit2
import retrofit2.Response
import retrofit2.http.Part
import java.io.File


class FitcalRepository(private val apiService: ApiService) {
    fun predictSleep(request: SleepRequest): LiveData<FitcalResponse?> {
        val liveData = MutableLiveData<FitcalResponse?>()
        apiService.predectedSleep(request)
            .enqueue(object : Callback<FitcalResponse> {
                override fun onResponse(
                    call: Call<FitcalResponse>,
                    response: Response<FitcalResponse>
                ) {
                    liveData.value = response.body()
                }

                override fun onFailure(call: Call<FitcalResponse>, t: Throwable) {
                    liveData.value = null
                }
            })
        return liveData
    }

    fun predictPhysical(request: PhysicalRequest): LiveData<FitcalResponse?> {
        val liveData = MutableLiveData<FitcalResponse?>()
        apiService.predectedPhysical(request)
            .enqueue(object : Callback<FitcalResponse> {
                override fun onResponse(
                    call: Call<FitcalResponse>,
                    response: Response<FitcalResponse>
                ) {
                    liveData.value = response.body()
                }

                override fun onFailure(call: Call<FitcalResponse>, t: Throwable) {
                    liveData.value = null
                }

            })
        return liveData
    }

//    fun predictCalories(
//        photo: File,
//        water: Number,
//        protein: Number,
//        lipid: Number,
//        ash: Number,
//        carbohydrate: Number,
//        fiber: Number,
//        sugar: Number,
//
//        ): LiveData<FitcalResponse?> {
//        val liveData = MutableLiveData<FitcalResponse?>()
//        val photoBytes = photo.readBytes()
//        val filePart = photoBytes.asRequestBody("image/jpeg".toMediaTypeOrNull())
//            .let { MultipartBody.Part.createFormData("photo", photo.name, it) }
//        val waterRequestBody = water.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//        val proteinRequestBody = protein.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//        val lipidRequestBody = lipid.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//        val ashRequestBody = ash.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//        val carbohydrateRequestBody = carbohydrate.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//        val fiberRequestBody = fiber.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//        val sugarRequestBody = sugar.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//
//        apiService.predictCalories(filePart, waterRequestBody, proteinRequestBody, lipidRequestBody, ashRequestBody, carbohydrateRequestBody, fiberRequestBody, sugarRequestBody)
//            .enqueue(object : Callback<FitcalResponse> {
//                override fun onResponse(
//                    call: Call<FitcalResponse>,
//                    response: Response<FitcalResponse>
//                ) {
//                    liveData.value = response.body()
//                }
//
//                override fun onFailure(call: Call<FitcalResponse>, t: Throwable) {
//                    liveData.value = null
//                }
//            })
//
//        return liveData
//    }
fun predictCalories(
    photo: File,
    water: Number,
    protein: Number,
    lipid: Number,
    ash: Number,
    carbohydrate: Number,
    fiber: Number,
    sugar: Number,
): LiveData<FitcalResponse?> {
    val liveData = MutableLiveData<FitcalResponse?>()

    // Baca file menjadi byte array
    val photoBytes = photo.readBytes()

    // Konversi byte array menjadi RequestBody
    val filePart = photoBytes.toRequestBody("image/jpeg".toMediaTypeOrNull())
        .let { MultipartBody.Part.createFormData("image", photo.name, it) }

    val waterRequestBody = water.toString().toRequestBody("text/plain".toMediaTypeOrNull())
    val proteinRequestBody = protein.toString().toRequestBody("text/plain".toMediaTypeOrNull())
    val lipidRequestBody = lipid.toString().toRequestBody("text/plain".toMediaTypeOrNull())
    val ashRequestBody = ash.toString().toRequestBody("text/plain".toMediaTypeOrNull())
    val carbohydrateRequestBody = carbohydrate.toString().toRequestBody("text/plain".toMediaTypeOrNull())
    val fiberRequestBody = fiber.toString().toRequestBody("text/plain".toMediaTypeOrNull())
    val sugarRequestBody = sugar.toString().toRequestBody("text/plain".toMediaTypeOrNull())

    // Mengirimkan data ke API
    apiService.predictCalories(
        filePart,
        waterRequestBody,
        proteinRequestBody,
        lipidRequestBody,
        ashRequestBody,
        carbohydrateRequestBody,
        fiberRequestBody,
        sugarRequestBody
    ).enqueue(object : Callback<FitcalResponse> {
        override fun onResponse(
            call: Call<FitcalResponse>,
            response: Response<FitcalResponse>
        ) {
            liveData.value = response.body()
        }

        override fun onFailure(call: Call<FitcalResponse>, t: Throwable) {
            liveData.value = null
        }
    })

    return liveData
}


}




