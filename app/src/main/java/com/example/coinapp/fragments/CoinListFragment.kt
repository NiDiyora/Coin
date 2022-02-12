package com.example.coinapp.fragments

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coinapp.Adapters.CoinListAdapter
import com.example.coinapp.Preferences.PreferenceUtil
import com.example.coinapp.R
import com.example.coinapp.Utils.ApiState
import com.example.coinapp.ViewModels.CoinViewmodel
import com.example.coinapp.databinding.FragmentCoinListBinding
import com.example.coinapp.model.Coin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class CoinListFragment : Fragment() {


    private val coinViewmodel: CoinViewmodel by viewModels()
    val coinListAdapter: CoinListAdapter = CoinListAdapter(ArrayList())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentCoinListBinding: FragmentCoinListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_coin_list, container, false)
        // Inflate the layout for this fragment

        lifecycleScope.launchWhenCreated {
            coinViewmodel._coinStateFlow.collect {
                when (it) {
                    ApiState.Loading -> {
                        fragmentCoinListBinding.coinRecyclerview.isVisible = false
                        fragmentCoinListBinding.progrssbar.visibility = View.VISIBLE
                    }
                    is ApiState.Failure -> {
                        fragmentCoinListBinding.coinRecyclerview.isVisible = false
                        fragmentCoinListBinding.progrssbar.isVisible = false

                        Log.d("Tag", "On Create: ${it.msg}")

                    }
                    is ApiState.Success -> {
                        fragmentCoinListBinding.coinRecyclerview.isVisible = true
                        fragmentCoinListBinding.progrssbar.isVisible = false
                        coinListAdapter.setCoinData(it.data)
                        PreferenceUtil.saveArray(it.data)


                        // ModelPreferencesManager.put(it.data,"setdata")
                      //  val data = ModelPreferencesManager.get<List<Coin>>("setdata")
//                        saveData(it.data)
//                        loadData()
                    }
                }
            }
        }
        coinViewmodel.getCoin()
        fragmentCoinListBinding.coinRecyclerview.adapter = coinListAdapter
        coinListAdapter.onItemClick = {
        }
        return fragmentCoinListBinding.root
    }

    private fun saveData(coin:List<Coin>) {
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(coin)
        editor.putString("task list", json)
        editor.apply()
    }

    private fun loadData() {
        var coin1:List<Coin>
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("task list", null)
        val type = object : TypeToken<ArrayList<Coin?>?>() {}.type
        coin1 = gson.fromJson<List<Coin>>(json, type)
        if (coin1 == null) {
            coin1 = ArrayList()


        }
        coinListAdapter.setCoinData(coin1)
    }
}