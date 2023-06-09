package jlmd.dev.android.catsapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppError(
    @field:Json(name = "code") val code: Int? = null,
    @field:Json(name = "statusCode") val statusCode: Int? = null,
    @field:Json(name = "message") val message: String? = null
)