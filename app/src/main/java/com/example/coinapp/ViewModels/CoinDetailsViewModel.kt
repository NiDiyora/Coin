package com.example.coinapp.ViewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapp.Network.CoinApiServiceImpl
import com.example.coinapp.Network.CoinDetailsRepository
import com.example.coinapp.Utils.CoinDetailsState
import com.example.coinapp.Utils.executeAsyncTask
import com.example.coinapp.db.CoinDao
import com.example.coinapp.db.CoinDbRepository
import com.example.coinapp.db.Database
import com.example.coinapp.model.Coin
import com.example.coinapp.model.CoinDetails
import com.github.barteksc.pdfviewer.PDFView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val coinApiServiceImpl: CoinApiServiceImpl,
    private val coinDetailsRepository: CoinDetailsRepository,
) : ViewModel() {


    val coinDetailsStateFlow: MutableStateFlow<CoinDetailsState> =
        MutableStateFlow(CoinDetailsState.Empty)
    var _coinDetailsStateFlow: SharedFlow<CoinDetailsState> = coinDetailsStateFlow

    fun getCoinDetails(coinId: String) = viewModelScope.launch {
        coinDetailsStateFlow.value = CoinDetailsState.Loading
        coinDetailsRepository.getCoinDetails(coinId).catch { e ->
            coinDetailsStateFlow.value = CoinDetailsState.Failure(e)
        }.collect {
            coinDetailsStateFlow.value = CoinDetailsState.Success(it)
        }
    }

    fun addCoinDetails(coin: CoinDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            coinDetailsRepository.addCoinDetails(coin)
        }
    }

    fun pdfViewer(url: String) {
        viewModelScope.executeAsyncTask(onPostExecute = {

        }, doInBackground = {
            var inputStream: InputStream? = null
            try {
                val pdfUrl = URL(url)
                // below is the step where we are
                // creating our connection.
                // below is the step where we are
                // creating our connection.
                val urlConnection: HttpURLConnection = pdfUrl.openConnection() as HttpsURLConnection
                if (urlConnection.responseCode === 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = BufferedInputStream(urlConnection.getInputStream())
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }, onPreExecute = {

        })
    }
}