package jlmd.dev.android.catsapp.data.di

import jlmd.dev.android.catsapp.RetrofitClients
import jlmd.dev.android.catsapp.data.service.CatsAPI
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {

    single<CatsAPI> {
        provideService(get(named(RetrofitClients.CATS_API.name)))
    }
}

private inline fun <reified T> provideService(retrofit: Retrofit): T = retrofit.create(T::class.java)
