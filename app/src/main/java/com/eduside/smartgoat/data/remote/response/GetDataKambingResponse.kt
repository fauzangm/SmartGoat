package com.eduside.smartgoat.data.remote.response

data class GetDataKambingResponse(
	val data: List<DataKambingItem>? = null,
	val message: String? = null,
	val status: Boolean? = null
)

data class DataKambingItem(
	val warna: String? = null,
	val saudara: String? = null,
	val image: String? = null,
	val rfid_jantan: String? = null,
	val gender: String? = null,
	val ras: String? = null,
	val rfid_betina: String? = null,
	val ttl: String? = null,
	val lokasi: String? = null,
	val bobot_sekarang: String? = null,
	val rfid: String? = null,
	val id: Int? = null,
	val time: String? = null,
	val bobot_lahir: String? = null
)

