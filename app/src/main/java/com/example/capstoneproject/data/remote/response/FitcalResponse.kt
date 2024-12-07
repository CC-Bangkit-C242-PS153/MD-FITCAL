package com.example.capstoneproject.data.remote.response

import com.google.gson.annotations.SerializedName

data class FitcalResponse(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class Activities2(
	@field:SerializedName("activity")
	val activity: String? = null,
	@field:SerializedName("reason")
	val reason: String? = null


)

data class Result(
	@field:SerializedName("result")
	val result: String?,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("suggestion")
	val suggestion: Suggestion? = null,

	@field:SerializedName("InferenceId")
	val inferenceId: String? = null,

	@field:SerializedName("foodCalories")
	val foodCalories: Any? = null,

	@field:SerializedName("foodCaloriesClass")
	val foodCaloriesClass: Any? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("foodCategory")
	val foodCategory: String? = null
)

data class Activities1(
	@field:SerializedName("activity")
	val activity: String? = null,

	@field:SerializedName("reason")
	val reason: String? = null


)

data class Suggestion(

	@field:SerializedName("activtities1")
	val activities1: Activities1? = null,

	@field:SerializedName("activtities2")
	val activities2: Activities2? = null
)
