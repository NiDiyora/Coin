package com.example.coinapp.Network

import com.example.coinapp.model.Coin
import javax.inject.Inject

class CoinApiServiceImpl @Inject constructor(private val coinApiService: CoinApiService) {

    suspend fun getCoin(): List<Coin> = coinApiService.getCoin()

}