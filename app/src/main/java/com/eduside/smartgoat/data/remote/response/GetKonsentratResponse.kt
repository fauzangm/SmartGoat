package com.eduside.smartgoat.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetKonsentratResponse(

	@field:SerializedName("data")
	val data: DataKonsentrat? = null,

	@field:SerializedName("message")
	val message: MessageKonsentrat? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataKonsentrat(

	@field:SerializedName("konsentrat")
	val konsentrat: List<KonsentratItem?>? = null
)

data class KonsentratItem(

	@field:SerializedName("umur")
	val umur: Int? = null,

	@field:SerializedName("presentase")
	val presentase: String? = null,

	@field:SerializedName("bobot_sekarang")
	val bobotSekarang: String? = null,

	@field:SerializedName("konsentrat")
	val konsentrat: Any? = null
)

data class MessageKonsentrat(

	@field:SerializedName("id_kambing")
	val idKambing: String? = null
)
