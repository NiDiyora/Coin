package com.example.coinapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.coinapp.model.Coin
import com.example.coinapp.model.CoinDetails

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCoin(coin: List<Coin>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCoinDetails(coindetails: CoinDetails)
}