package com.example.coinapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Coin_List_tbl")
data class Coin(
    @PrimaryKey
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)