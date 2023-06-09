package jlmd.dev.android.catsapp.data.service.gateway.dto

sealed class ApiResponse<out R> {
    val isSuccessful: Boolean
        get() = this is Success

    data class Success<out T>(
        val response: T
    ): ApiResponse<T>()

    data class Error(
        val httpCode: Int,
        val backendCode: Int?
    ): ApiResponse<Nothing>()
}
