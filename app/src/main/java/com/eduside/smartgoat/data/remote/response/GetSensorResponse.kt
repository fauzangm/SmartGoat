package com.eduside.smartgoat.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetSensorResponse(

	@field:SerializedName("data")
	val data: List<DataSensorItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataSensorItem(

	@field:SerializedName("intensitas")
	val intensitas: String? = null,

	@field:SerializedName("suhu")
	val suhu: String? = null,

	@field:SerializedName("amonia")
	val amonia: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("kelembapan")
	val kelembapan: String? = null
)
