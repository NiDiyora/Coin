package com.example.coinapp.Utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.coinapp.model.Links
import com.example.coinapp.model.LinksExtended
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodItemToString(foodItems: List<LinksExtended>): String {
        return gson.toJson(foodItems)
    }

    @TypeConverter
    fun stringToFoodItem(data: String): List<LinksExtended> {
        val listType = object : TypeToken<List<LinksExtended>>() {
        }.type
        return gson.fromJson(data, listType)
    }
//    @TypeConverter
//    fun fromString(value: String?): List<Links?>? {
//        val listType = object : TypeToken<List<Links?>?>() {}.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromArrayLisr(list: List<Links?>?): String? {
//        val gson = Gson()
//        return gson.toJson(list)
//    }


//
//    @TypeConverter // note this annotation
//    fun fromLinksValuesList(optionValues: List<Links>?) = Gson().toJson(optionValues)

//    @TypeConverter // note this annotation
//    fun toLinksValuesList(optionValuesString: String?) =
//        Gson().fromJson(optionValuesString, Array<Links>::class.java).toList()

}