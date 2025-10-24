package com.example.responsi1mobileh1d023073.api
// Lokasi: com.yournim.responsi1mobile.api/RetrofitClient.kt

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.football-data.org/"

    // Ganti dengan token API Anda
    const val API_TOKEN = "0a00be35bbb24ddbb7a57d931c22fdaf"

    // Konfigurasi Interceptor untuk mencatat (logging) setiap permintaan/respons
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        // Level BODY mencakup header dan body data (JSON)
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Konfigurasi Klien HTTP (OkHttp)
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        // Anda bisa menambahkan timeouts atau caching di sini
        .build()

    // Inisialisasi Retrofit
    val apiService: FootballApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Menentukan klien HTTP yang digunakan
            .client(okHttpClient)
            // Menentukan konverter (Gson) untuk JSON
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            // Membuat implementasi dari FootballApiService
            .create(FootballApiService::class.java)
    }
}