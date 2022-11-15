package com.eduside.smartgoat.data.remote.response

import com.google.gson.annotations.SerializedName

data class PostRegistResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class Data(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("lokasi_kandang")
	val lokasiKandang: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("domisili")
	val domisili: String? = null,

	@field:SerializedName("ttl")
	val ttl: String? = null,

	@field:SerializedName("nodeid")
	val nodeid: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
