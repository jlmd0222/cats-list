package jlmd.dev.android.catsapp.view.di.modules

import jlmd.dev.android.catsapp.view.cats.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { MainViewModel(get()) }
}