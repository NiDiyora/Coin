package com.example.coinapp.ViewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapp.Network.CoinRepository
import com.example.coinapp.Utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinViewmodel @Inject constructor(private val coinRepository: CoinRepository,application:Application) : ViewModel() {
 //   val userDao = DataBaseRoom.getdatabase(application).coinDao()
    val coinStateFlow:MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    val _coinStateFlow: StateFlow<ApiState> = coinStateFlow

    fun getCoin() = viewModelScope.launch {
        coinStateFlow.value = ApiState.Loading
        coinRepository.getcoin().catch { e->
            coinStateFlow.value = ApiState.Failure(e)
        }.collect {
          //  coinRepository.AddCoin(it)
            coinStateFlow.value = ApiState.Success(it)
        }


}
}