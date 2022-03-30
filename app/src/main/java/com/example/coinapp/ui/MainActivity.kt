package com.example.coinapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.coinapp.Preferences.PreferenceUtil
import com.example.coinapp.R
import com.example.coinapp.Utils.OnCoinClickListener
import com.example.coinapp.model.Coin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnCoinClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findNavController(R.id.fragmentContainerView)
        PreferenceUtil.user().get()
        PreferenceUtil.clearPreference()
    }

    override fun onItemClickLister(coinId: String) {

    }


}