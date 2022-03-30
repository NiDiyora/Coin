package com.example.coinapp.db

import com.example.coinapp.model.Coin
import com.example.coinapp.model.CoinDetails

class CoinDbRepository constructor(private val coinDao: CoinDao) {

    fun addCoin(coin: List<Coin>) {
       // coinDao.addCoin(coin)
    }


}