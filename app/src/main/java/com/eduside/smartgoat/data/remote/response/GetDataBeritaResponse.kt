package com.eduside.smartgoat.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetDataBeritaResponse(

	@field:SerializedName("data")
	val data: List<DataBeritaItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataBeritaItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("penulis")
	val penulis: String? = null,

	@field:SerializedName("referensi")
	val referensi: String? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("isi")
	val isi: String? = null
)
