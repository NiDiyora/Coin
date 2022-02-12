package com.example.coinapp

import android.app.Application
import com.example.coinapp.Preferences.CommonsCore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoinApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        CommonsCore.init(this)
    }
}