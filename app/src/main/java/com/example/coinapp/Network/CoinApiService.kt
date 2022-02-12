package com.example.coinapp.Network

import com.example.coinapp.model.Coin
import retrofit2.http.GET

interface CoinApiService {

    @GET("coins")
    suspend fun getCoin():List<Coin>
}