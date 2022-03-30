package com.example.coinapp.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.coinapp.R
import com.example.coinapp.Utils.CoinDetailsState
import com.example.coinapp.Utils.executeAsyncTask
import com.example.coinapp.ViewModels.CoinDetailsViewModel
import com.example.coinapp.databinding.FragmentCoinDetailsBinding
import com.example.coinapp.model.Links
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import javax.net.ssl.HttpsURLConnection


@AndroidEntryPoint
class CoinDetailsFragment : Fragment() {

    private val coinDetailsViewModel: CoinDetailsViewModel by viewModels()
    var coinid: String? = null
          var gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coinid = requireArguments().getString("coinid")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentCoinDetailsBinding: FragmentCoinDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_coin_details, container, false)

        lifecycleScope.launchWhenCreated {
            coinDetailsViewModel._coinDetailsStateFlow.collect {
                when (it) {
                    is CoinDetailsState.Loading -> {
                        fragmentCoinDetailsBinding.progressbar.visibility = View.VISIBLE
                    }
                    is CoinDetailsState.Empty -> {
                        fragmentCoinDetailsBinding.progressbar.visibility = View.GONE
                        Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                    }
                    is CoinDetailsState.Success -> {
                        coinDetailsViewModel.addCoinDetails(it.data)
                        fragmentCoinDetailsBinding.progressbar.visibility = View.GONE
                        fragmentCoinDetailsBinding.pdfViewer.fromUri(Uri.parse(it.data.whitepaper?.link))
                        foodItemToString(it.data.links)
                        var inputStream: InputStream? = null


                        lifecycleScope.executeAsyncTask(onPreExecute = {
                        }, doInBackground = {
                            try {
                                val pdfUrl = URL(it.data.whitepaper?.link)
                                val urlConnection: HttpURLConnection =
                                    pdfUrl.openConnection() as HttpsURLConnection
                                if (urlConnection.responseCode === 200) {

                                    inputStream =
                                        BufferedInputStream(urlConnection.inputStream)
                                }
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }, onPostExecute = {

                            fragmentCoinDetailsBinding.pdfViewer.fromStream(inputStream).load()
                            // runs in Main Thread
                            // ... here "it" is the data returned from "doInBackground"
                        })
//                        fragmentCoinDetailsBinding.description.text = it.data.description
//                        Glide.with(this@CoinDetailsFragment).load(it.data.whitepaper.thumbnail)
//                            .into(fragmentCoinDetailsBinding.thumbnail)
                    }
                    else -> {

                    }
                }
            }
        }


        coinid?.let { coinDetailsViewModel.getCoinDetails(it) }
        return fragmentCoinDetailsBinding.root
    }


    fun foodItemToString(foodItems: Links): String {
        return gson.toJson(foodItems)
    }

}