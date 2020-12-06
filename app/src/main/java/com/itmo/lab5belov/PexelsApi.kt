package com.itmo.lab5belov

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface PexelsApi {

    @Headers("Authorization: 563492ad6f91700001000001d82c69ea00984ce8ab819e9d00e3b4cb")
    @GET("search")
    fun getPics(@Query("query") query: String): Call<ResponseJson>
}