package com.example.coinapp.Utils

import com.example.coinapp.model.Coin

sealed class ApiState {
    data class Success(val data: List<Coin>):ApiState()
    data class Failure(val msg: Throwable):ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}
