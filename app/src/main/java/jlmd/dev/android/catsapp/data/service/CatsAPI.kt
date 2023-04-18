package jlmd.dev.android.catsapp.data.service

import jlmd.dev.android.catsapp.data.service.gateway.dto.CatResponse
import retrofit2.Response
import retrofit2.http.GET

interface CatsAPI {

    @GET("/v1/breeds")
    suspend fun getCats(): Response<List<CatResponse>>
}
