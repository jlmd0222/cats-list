package jlmd.dev.android.catsapp.core.di

import jlmd.dev.android.catsapp.core.CatsRepository
import org.koin.dsl.module

val repositoriesModule = module {

    single { CatsRepository(get()) }

}