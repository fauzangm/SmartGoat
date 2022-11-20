package com.eduside.smartgoat.data.remote.response

import com.google.gson.annotations.SerializedName

data class PutTimbanganResponse(

	@field:SerializedName("data")
	val data: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)
