package jlmd.dev.android.catsapp.data.service.gateway

import jlmd.dev.android.catsapp.data.BaseGateway
import jlmd.dev.android.catsapp.data.service.CatsAPI

class CatsGateway(
    private val catsAPI: CatsAPI
): BaseGateway() {

    suspend fun getCats() = getResult {
            executeRequest {
                catsAPI.getCats()
            }
        }
}
