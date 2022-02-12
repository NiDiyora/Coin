package com.example.coinapp.Preferences

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object CommonsCore {
    var context: Context? = null

    fun init(context: Context?) {
        if (CommonsCore.context == null) {
            CommonsCore.context = context
        }
    }
}