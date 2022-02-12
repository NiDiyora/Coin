package com.example.coinapp.Preferences

import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.lang.Exception

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


abstract class BaseListPreference {

    protected var clazz: Class<Any>? = null
    var key: String? = null
    constructor(clazz:Class<Any>){
        this.clazz = clazz
    }
    constructor(clazz:Class<Any>,key:String){
        this.clazz = clazz
        this.key = key
    }

    open fun get(): List<Any?>? {
        val serialized: String =
          //  BasePreferenceUtil.stringPreference(AuthenticatorKeys.getKey()).get()
        return try {
            null
          //  parse(serialized)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    open fun set(list: List<Any?>?) {
        if (list == null) {
            //BasePreferenceUtil.stringPreference(AuthenticatorKeys.getKey()).set("")
            return
        }
        try {
           // BasePreferenceUtil.stringPreference(AuthenticatorKeys.getKey()).set(serialize(list))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

      @JvmName("getKey1")
      fun getKey(): String? {
        return if (key == null) clazz!!.canonicalName else key
    }

    //Use any json parsing library, or serialize/parse manually
    @Throws(Exception::class)
    abstract fun serialize(list: List<Any?>?): String?

    @Throws(Exception::class)
    abstract fun parse(jsonString: String?): List<Any?>?

}