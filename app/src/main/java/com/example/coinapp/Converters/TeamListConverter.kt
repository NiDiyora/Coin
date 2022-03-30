package com.example.coinapp.Converters

import androidx.room.TypeConverter
import com.example.coinapp.model.LinksExtended
import com.example.coinapp.model.Team
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TeamListConverter {
    var gson = Gson()

    @TypeConverter
    fun teamListToString(foodItems: List<Team>): String {
        return gson.toJson(foodItems)
    }

    @TypeConverter
    fun stringToTeamList(data: String): List<Team> {
        val listType = object : TypeToken<List<Team>>() {
        }.type
        return gson.fromJson(data, listType)
    }
}