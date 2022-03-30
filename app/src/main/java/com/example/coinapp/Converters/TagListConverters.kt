package com.example.coinapp.Converters

import com.google.gson.Gson
import androidx.room.TypeConverter
import com.example.coinapp.model.Links
import com.example.coinapp.model.Tag
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class TagListConverters {

    @TypeConverter
    fun stringToListOfStrings(value: String): ArrayList<Tag> {
        val listType: Type = object : TypeToken<ArrayList<Tag?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listOfStringsToString(list: List<Tag>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}