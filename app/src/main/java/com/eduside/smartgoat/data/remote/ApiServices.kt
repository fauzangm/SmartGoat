package com.eduside.smartgoat.data.remote

import com.eduside.smartgoat.data.remote.response.*
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {

    // Regist
//    @FormUrlEncoded
    @POST("auth/add")
    suspend fun postRegist(
        @Body body: JsonObject
//        @Field("email")email: String?,
//        @Field("ttl")ttl: String?,
//        @Field("lokasi_kandang")lokasi_kandang: String?,
//        @Field("domisili")domisili: String?,
//        @Field("nodeid")nodeid: Int?,
//        @Field("name")name: String?,
//        @Field("password")password: String?,
//        @Field("password_confirmation")password_confirmation: String?,
    ): Response<PostRegistResponse>

    // Put Switch TImbangan
    @PUT("nodes/switch/timbangan")
    suspend fun putTimbangan(
        @Body body: JsonObject
    ): Response<PutTimbanganResponse>

    // Put Switch Fan
    @PUT("nodes/switch/exhouse")
    suspend fun putFan(
        @Body body: JsonObject
    ): Response<PutTimbanganResponse>

    // Put Switch Lamp
    @PUT("nodes/switch/dimmer")
    suspend fun putSwLampu(
        @Body body: JsonObject
    ): Response<PutTimbanganResponse>

    // Put Dimmer Lamp
    @PUT("nodes/dimmer")
    suspend fun putDimmer(
        @Body body: JsonObject
    ): Response<PutTimbanganResponse>

    // GET SENSOR
    @GET("nodes/sensor/all")
    suspend fun getSensor(): Response<GetSensorResponse>

    // GET SENSOR
    @GET("nodes/image/all")
    suspend fun getAllImage(): Response<GetImageResponse>

    // GET Switch
    @GET("nodes/switch/1")
    suspend fun getAllSw(): Response<GetSwitchResponse>


    // Login
    @POST("auth/login")
    suspend fun postLogin(
        @Body body: JsonObject
    ): Response<PostLoginResponse>

    // GET DATA KAMBING
    @GET("data/kambing")
    suspend fun getDataKambing(): Response<GetDataKambingResponse>

    // GET DATA BERITA
    @GET("data/news")
    suspend fun getBerita(): Response<GetDataBeritaResponse>

    // GET DATA KONSENTRAT
    @FormUrlEncoded
    @POST("data/kambing/konsentrat")
    suspend fun getKonsentrat(
        @Field("id")id : String
    ): Response<GetKonsentratResponse>

    // GET DATA HIJAUAN
    @FormUrlEncoded
    @POST("data/kambing/hijauan")
    suspend fun getHijauan(
        @Field("id")id : String
    ): Response<GetHijauanResponse>

}