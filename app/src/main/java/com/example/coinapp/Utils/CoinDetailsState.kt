package com.example.coinapp.Utils

import com.example.coinapp.model.Coin
import com.example.coinapp.model.CoinDetails

sealed class CoinDetailsState {
    data class Success(val data: CoinDetails):CoinDetailsState()
    data class Failure(val msg: Throwable):CoinDetailsState()
    object Loading : CoinDetailsState()
    object Empty : CoinDetailsState()
}