package com.example.pertemuan12.mahasiswaapplication

import android.app.Application
import com.example.pertemuan12.depedenciesinjection.AppContainer
import com.example.pertemuan12.depedenciesinjection.MahasiswaContainer


class MahasiswaApplications : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}