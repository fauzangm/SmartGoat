package com.eduside.smartgoat.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetHijauanResponse(

	@field:SerializedName("data")
	val data: DataHijauan? = null,

	@field:SerializedName("message")
	val message: MessageHijauan? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataHijauan(

	@field:SerializedName("hijauan")
	val hijauan: List<HijauanItem?>? = null
)

data class MessageHijauan(

	@field:SerializedName("id_kambing")
	val idKambing: String? = null
)

data class HijauanItem(

	@field:SerializedName("hijauan")
	val hijauan: Int? = null,

	@field:SerializedName("umur")
	val umur: Int? = null,

	@field:SerializedName("presentase")
	val presentase: String? = null,

	@field:SerializedName("bobot_sekarang")
	val bobotSekarang: String? = null
)
