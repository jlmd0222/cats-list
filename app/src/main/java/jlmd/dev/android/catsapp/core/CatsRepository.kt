package jlmd.dev.android.catsapp.core

import jlmd.dev.android.catsapp.data.service.gateway.CatsGateway
import jlmd.dev.android.catsapp.common.RequestResult
import jlmd.dev.android.catsapp.common.thenSuspending
import jlmd.dev.android.catsapp.core.model.Cat
import jlmd.dev.android.catsapp.utils.toCat

class CatsRepository(
    private val catsGateway: CatsGateway
) {

    suspend fun getCatsFromApi(): RequestResult<List<Cat>> {
        return catsGateway.getCats()
            .thenSuspending { catsResponse ->
                val users = catsResponse.orEmpty()
                    .distinctBy { cat -> cat.id }
                    .map { it.toCat() }

                RequestResult.Success(users)
            }
    }
}
