package com.example.venuebooker.Controller

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {
    companion object {
        private const val apiUrl = "http://43.205.87.112:8080"
        private const val apiKey = "80b856a2acc361a849858e8123ccef26bef7452f11403072024160737"
        private lateinit var retrofit: Retrofit

        fun getInstance(): ApiInterface {
            // Create an interceptor to add headers
            val headerInterceptor = Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-api-key", apiKey)
                    .build()
                chain.proceed(request)
            }

            // Setup OkHttpClient with logging and header interceptor
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .cache(null)
                .build()

            // Setup Retrofit with OkHttpClient
            retrofit = Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}
