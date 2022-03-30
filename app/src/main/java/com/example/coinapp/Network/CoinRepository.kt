package com.example.coinapp.Network

import com.example.coinapp.db.CoinDao
import com.example.coinapp.model.Coin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CoinRepository @Inject constructor(private val coinApiServiceImpl: CoinApiServiceImpl,private val dao: CoinDao) {

    suspend fun getcoin(): Flow<List<Coin>> = flow {
        emit(coinApiServiceImpl.getCoin())
    }.flowOn(Dispatchers.IO)

    suspend fun addCoin(coin:List<Coin>){
        dao.addCoin(coin)
    }

}