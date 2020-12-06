package com.itmo.lab5belov

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ResponseJson(@Json(name = "total_results") val total_results: Int,
                   @Json(name = "page") val page: Int,
                   @Json(name = "per_page") val per_page: Int,
                   @Json(name = "photos") val photos: List<Picture>,
               ) {

}