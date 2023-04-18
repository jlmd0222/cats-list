package jlmd.dev.android.catsapp.core.usecases

import jlmd.dev.android.catsapp.core.CatsRepository
import jlmd.dev.android.catsapp.common.RequestResult
import jlmd.dev.android.catsapp.core.model.Cat

class GetCatsUseCase(
    private val catsRepository: CatsRepository
) {

    suspend fun invoke(): RequestResult<List<Cat>> {
        return catsRepository.getCatsFromApi()
    }
}
