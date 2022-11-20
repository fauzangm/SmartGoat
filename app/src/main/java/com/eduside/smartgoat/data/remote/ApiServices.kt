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

    // Put TImbangan
    @PUT("nodes/switch/timbangan")
    suspend fun putTimbangan(
        @Body body: JsonObject
    ): Response<PutTimbanganResponse>

    // Put Fan
    @PUT("nodes/switch/exhouse")
    suspend fun putFan(
        @Body body: JsonObject
    ): Response<PutTimbanganResponse>

    // GET SENSOR
    @GET("nodes/sensor/all")
    suspend fun getSensor(): Response<GetSensorResponse>


    // Login
    @POST("auth/login")
    suspend fun postLogin(
        @Body body: JsonObject
    ): Response<PostLoginResponse>

}