package com.techullurgy.dosthman

import android.app.Application
import com.techullurgy.dosthman.di.initApp
import org.koin.android.ext.koin.androidContext

class DosthManApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initApp {
            androidContext(this@DosthManApplication)
        }
    }
}