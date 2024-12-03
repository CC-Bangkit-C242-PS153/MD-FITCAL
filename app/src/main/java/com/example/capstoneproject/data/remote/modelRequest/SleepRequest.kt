package com.example.capstoneproject.data.remote.modelRequest

data class SleepRequest(
    val gender: Int,
    val age: Int,
    val sleepDuration: Double,
    val qualitySleep: Int,
    val physicalActivity: Int,
    val stressLevel: Int,
    val BMI: Int,
    val heartRate: Int,
    val dailySteps: Int,
    val systolic: Int,
    val diastolic:Int
)
