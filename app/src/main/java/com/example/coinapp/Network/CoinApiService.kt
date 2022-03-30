package com.example.coinapp.Network

import com.example.coinapp.model.Coin
import com.example.coinapp.model.CoinDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApiService {

    @GET("coins")
    suspend fun getCoin():List<Coin>

    @GET("coins/{coinId}")
    suspend fun getCoinDetails(@Path("coinId") coinId:String):CoinDetails
}