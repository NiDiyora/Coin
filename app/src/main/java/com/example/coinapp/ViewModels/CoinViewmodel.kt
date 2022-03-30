package com.example.coinapp.ViewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapp.Network.CoinRepository
import com.example.coinapp.Utils.ApiState
import com.example.coinapp.db.CoinDbRepository
import com.example.coinapp.db.Database
import com.example.coinapp.model.Coin
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject


@HiltViewModel
class CoinViewmodel @Inject constructor(
    private val coinRepository: CoinRepository,
) : ViewModel() {


    //   val userDao = DataBaseRoom.getdatabase(application).coinDao()
    val coinStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val _coinStateFlow: StateFlow<ApiState> = coinStateFlow


    fun getCoin() = viewModelScope.launch {
        coinStateFlow.value = ApiState.Loading
        coinRepository.getcoin().catch { e ->
            coinStateFlow.value = ApiState.Failure(e)
        }.collect {
            //  coinRepository.AddCoin(it)
            coinStateFlow.value = ApiState.Success(it)
        }
    }

    fun addCoin(coin: List<Coin>) {
        viewModelScope.launch(Dispatchers.IO) {
            coinRepository.addCoin(coin)
        }
    }
}