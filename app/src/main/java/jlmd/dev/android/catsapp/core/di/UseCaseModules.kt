package jlmd.dev.android.catsapp.core.di

import jlmd.dev.android.catsapp.core.usecases.GetCatsUseCase
import org.koin.dsl.module

val useCaseModules = module {

    factory { GetCatsUseCase(get()) }
}