package com.example.coinapp.Network

import com.example.coinapp.db.CoinDao
import com.example.coinapp.model.CoinDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class CoinDetailsRepository @Inject constructor(
    private val coinApiServiceImpl: CoinApiServiceImpl,
    private val coinDao: CoinDao
) {

    suspend fun getCoinDetails(coinId: String): Flow<CoinDetails> = flow {
        emit(coinApiServiceImpl.getCoinDetails(coinId))
    }.flowOn(Dispatchers.IO)

    suspend fun addCoinDetails(coindetails:CoinDetails){
        coinDao.addCoinDetails(coindetails)
    }

}