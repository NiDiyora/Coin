package com.example.coinapp.Preferences

import com.google.gson.Gson

class ModelPreference<T> : BaseModelPreference<T> {
    constructor(clazz: Class<T>?) : super(clazz!!) {}

    constructor(clazz: Class<T>?, key: String?) : super(clazz!!, key) {}

    @Throws(Exception::class)
    override fun serialize(t: T): String {
        return Gson().toJson(t)
    }

    @Throws(Exception::class)
    override fun parse(jsonString: String?): T {
        return Gson().fromJson(jsonString, clazz)
    }
}