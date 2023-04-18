package jlmd.dev.android.catsapp

import android.app.Application
import jlmd.dev.android.catsapp.core.di.repositoriesModule
import jlmd.dev.android.catsapp.core.di.useCaseModules
import jlmd.dev.android.catsapp.data.di.serviceModule
import jlmd.dev.android.catsapp.view.di.modules.viewModelsModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

private val appModules = listOf<Module>() +
        viewModelsModule +
        networkModule +
        serviceModule +
        repositoriesModule +
        useCaseModules

@ExperimentalCoroutinesApi
class CatsApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()

        initDI()
    }

    private fun initDI() {
        val applicationCoroutineScopeModule = module {
            single { applicationScope }
        }

        startKoin {
            androidContext(this@CatsApplication)
            modules(
                appModules + applicationCoroutineScopeModule
            )
        }
    }
}