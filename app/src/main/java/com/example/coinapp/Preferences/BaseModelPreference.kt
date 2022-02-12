package com.example.coinapp.Preferences

abstract class BaseModelPreference<T> {
    protected var clazz: Class<T>
    var key: String? = null

    constructor(clazz: Class<T>) {
        this.clazz = clazz
    }

    constructor(clazz: Class<T>, key: String?) {
        this.clazz = clazz
        this.key = key
    }

    fun get(): T? {
        val serialized: String = PreferenceUtil.stringPreference(getKey())!!.get()
        return try {
            parse(serialized)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun set(t: T?) {
        if (t == null) {
            PreferenceUtil.stringPreference(getKey())!!.set("")
            return
        }
        try {
            PreferenceUtil.stringPreference(getKey())?.set(serialize(t))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmName("getKey1")
    private fun getKey(): String {
        return if (key == null) clazz.canonicalName else key!!
    }

    //Use any json parsing library, or serialize/parse manually
    @Throws(Exception::class)
    abstract fun serialize(t: T): String?
    @Throws(Exception::class)
    abstract fun parse(jsonString: String?): T
}
