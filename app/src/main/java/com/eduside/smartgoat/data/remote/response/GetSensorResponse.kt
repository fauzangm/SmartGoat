package com.eduside.smartgoat.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetSensorResponse(

	@field:SerializedName("data")
	val data: DataSensor? = null,

	@field:SerializedName("message")
	val message: List<String?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataSensor(

	@field:SerializedName("intensitas")
	val intensitas: String? = null,

	@field:SerializedName("suhu")
	val suhu: String? = null,

	@field:SerializedName("amonia")
	val amonia: String? = null,

	@field:SerializedName("kelembapan")
	val kelembapan: String? = null
)
