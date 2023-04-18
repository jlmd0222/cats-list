package jlmd.dev.android.catsapp

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import jlmd.dev.android.catsapp.data.service.gateway.CatsGateway
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

enum class RetrofitClients {
    CATS_API
}

enum class ConverterFactory {
    MOSHI
}

enum class OkHttpClients {
    DEFAULT
}

enum class Interceptors {
    API_TOKENS, LOGGING
}

enum class ApiKeyProviders {
    API_KEY
}

val networkModule = module {

    // Serialization / Deserialization
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single<Converter.Factory>(named(ConverterFactory.MOSHI.name)) { MoshiConverterFactory.create(get()) }

    //region interceptors
    single<Interceptor>(named(Interceptors.LOGGING.name)) { provideHttpLoggingInterceptor() }

    single(named(Interceptors.API_TOKENS.name)) {
        provideApiKeyInterceptor()
    }

    // region Http Clients
    single(named(OkHttpClients.DEFAULT.name)) {
        provideOkHttpClient(
            httpLoggingInterceptor = get(named(Interceptors.LOGGING.name))
        )
    }

    // region retrofit
    single(named(RetrofitClients.CATS_API.name)) {
        provideRetrofit(
            url = "https://api.thecatapi.com",
            client = get(named(OkHttpClients.DEFAULT.name)),
            factories = listOf(get(named(ConverterFactory.MOSHI.name)))
        )
    }

    single { CatsGateway(get()) }

}

fun provideOkHttpClient(httpLoggingInterceptor: Interceptor): OkHttpClient {

    val okHttpClientBuilder = OkHttpClient.Builder()

    okHttpClientBuilder
        .callTimeout(45, TimeUnit.SECONDS)
        .connectTimeout(45, TimeUnit.SECONDS)
        .readTimeout(45, TimeUnit.SECONDS)
        .writeTimeout(45, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)

    return okHttpClientBuilder.build()
}

fun provideRetrofit(
    url: String,
    client: OkHttpClient,
    factories: List<Converter.Factory>
): Retrofit {
    val builder = Retrofit.Builder()
        .baseUrl(url)
        .client(client)

    factories.forEach { builder.addConverterFactory(it) }

    return builder.build()
}

fun provideApiKeyInterceptor(): Interceptor {
    return object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return modifyChainRequest(chain)
        }

        private fun modifyChainRequest(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val apiKey = "bda53789-d59e-46cd-9bc4-2936630fde39"

            val newRequest = originalRequest.newBuilder()
                .header("x-api-key", apiKey)
                .method(originalRequest.method, originalRequest.body)
                .build()

            return chain.proceed(request = newRequest)
        }
    }
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor { message -> Log.d("NETWORK", "okhttp: $message") }
}