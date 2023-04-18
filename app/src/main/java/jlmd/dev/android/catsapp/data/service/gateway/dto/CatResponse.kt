package jlmd.dev.android.catsapp.data.service.gateway.dto

import com.squareup.moshi.Json

data class CatResponse(
    @Json(name = "id") val id: String,
    @Json(name = "name") val breedName: String,
    @Json(name = "origin") val origin: String,
    @Json(name = "affection_level") val affectionLevel: Int,
    @Json(name = "intelligence") val intelligence: Int,
    @Json(name = "reference_image_id") val imageUrl: String?
)
