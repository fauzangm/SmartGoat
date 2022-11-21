package com.eduside.smartgoat.data.remote.response

import com.google.gson.annotations.SerializedName

data class PutDImmerResponse(

	@field:SerializedName("data")
	val data: DataDimmer?,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataDimmer(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("dimmer")
	val dimmer: String? = null
)
