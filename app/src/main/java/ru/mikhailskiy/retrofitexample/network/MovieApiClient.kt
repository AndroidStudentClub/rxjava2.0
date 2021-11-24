package ru.mikhailskiy.retrofitexample.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.mikhailskiy.retrofitexample.network.logger.CustomHttpLogging

object MovieApiClient {

    const val BASE_URL = "https://api.themoviedb.org/3/"

    var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
                .newBuilder()
                .build()
            chain.proceed(request)
        })
        .addInterceptor(HttpLoggingInterceptor(CustomHttpLogging()).apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val apiClient: MovieApiInterface by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return@lazy retrofit.create(MovieApiInterface::class.java)
    }
}
