package com.example.pertemuan12.depedenciesinjection

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val kontakRepository: mahasiswaRepository
}

class MahasiswaContainer: AppContainer {

    private val baseUrl = "http://localhost:88/phpmyadmin/index.php?route=/database/structure&db=pam_api"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val mahasiswaService: MahasiswaService by lazy {
        retrofit.create(MahasiswaService::class.java)}

    override val kontakRepository: mahasiswaRepository by lazy {
        NetworkKontakRepository(mahasiswaService)
    }
}

