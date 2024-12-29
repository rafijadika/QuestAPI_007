package com.example.pertemuan12

import android.app.Application
import com.example.pertemuan12.depedenciesinjection.AppContainer
import com.example.pertemuan12.depedenciesinjection.MahasiswaContainer

class MahasiswaApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer(this)
    }

}