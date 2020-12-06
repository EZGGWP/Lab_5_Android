package com.itmo.lab5belov

import com.google.gson.JsonObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Picture(@Json(name = "id") val id: String,
                   @Json(name = "width") val width: Int,
                   @Json(name = "height") val height: Int,
                   @Json(name = "url") val url: String,
                   @Json(name = "photorapher") val photographer: String,
                   @Json(name = "photographer_url") val photographer_url: String,
                   @Json(name = "photographer_id") val photographer_id: Int,
                   @Json(name = "src") val src: Map<String, String>,
                   @Json(name = "liked") val liked: Boolean, ) {

}