package com.example.coinapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.coinapp.Converters.CommanTypeConverter
import com.example.coinapp.Converters.TagListConverters
import com.example.coinapp.Converters.TeamListConverter
import com.example.coinapp.Utils.TypeConverter
import com.example.coinapp.model.Coin
import com.example.coinapp.model.CoinDetails

@Database(entities = [Coin::class, CoinDetails::class], version = 2, exportSchema = false)
@TypeConverters(value = [TypeConverter::class, TagListConverters::class, TeamListConverter::class, CommanTypeConverter::class])
abstract class Database : RoomDatabase() {
    abstract fun coinDao(): CoinDao

//    companion object {
//        @Volatile
//        private var INSTANCE: com.example.coinapp.db.Database? = null
//
//        fun getdatabase(context: Context): com.example.coinapp.db.Database {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    com.example.coinapp.db.Database::class.java,
//                    "coin_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//
//        }
//    }
}