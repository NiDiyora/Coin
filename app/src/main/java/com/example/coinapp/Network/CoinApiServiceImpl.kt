package com.example.coinapp.Network

import com.example.coinapp.model.Coin
import com.example.coinapp.model.CoinDetails
import javax.inject.Inject

class CoinApiServiceImpl @Inject constructor(private val coinApiService: CoinApiService) {

    suspend fun getCoin(): List<Coin> = coinApiService.getCoin()

    suspend fun getCoinDetails(coinId:String):CoinDetails = coinApiService.getCoinDetails(coinId)

}