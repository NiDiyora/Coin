package com.example.coinapp.Converters

import androidx.room.TypeConverter
import com.example.coinapp.model.Team
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CommanTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun StringListToString(data: List<String>): String {
        return gson.toJson(data)
    }

    @TypeConverter
    fun stringToTeamList(data: String): List<String> {
        val listType = object : TypeToken<List<String>>() {
        }.type
        return gson.fromJson(data, listType)
    }
}