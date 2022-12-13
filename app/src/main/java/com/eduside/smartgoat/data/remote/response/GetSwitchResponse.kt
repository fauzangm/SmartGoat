package com.eduside.smartgoat.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetSwitchResponse(

	@field:SerializedName("data")
	val data: DataSwitch? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataSwitch(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("exhouse")
	val exhouse: String? = null,

	@field:SerializedName("timbangan")
	val timbangan: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("nodeid")
	val nodeid: String? = null,

	@field:SerializedName("dimmer")
	val dimmer: String? = null
)
