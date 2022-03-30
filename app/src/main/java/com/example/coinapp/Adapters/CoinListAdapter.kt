package com.example.coinapp.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coinapp.Preferences.PreferenceUtil
import com.example.coinapp.R
import com.example.coinapp.Utils.OnCoinClickListener
import com.example.coinapp.databinding.CoinItemListBinding
import com.example.coinapp.model.Coin

class CoinListAdapter constructor(private var coinlist: List<Coin>) :
    RecyclerView.Adapter<CoinListAdapter.CoinViewHolder>() {
    var onItemClick: ((Coin) -> Unit)? = null
    fun setCoinData(coinlist: List<Coin>) {
        this.coinlist = coinlist
        notifyDataSetChanged()
    }

    inner class CoinViewHolder(itemView: CoinItemListBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val coinItemListBinding = itemView

        @SuppressLint("ResourceAsColor")
        fun bind(coin: Coin) {
            if (coin.is_active) {
                coinItemListBinding.button.setBackgroundColor(R.color.black)
                coinItemListBinding.button.setTextColor(R.color.light_green)
                coinItemListBinding.button.setText("active")
            } else {
                coinItemListBinding.button.setBackgroundColor(R.color.teal_200)
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(coin)
            }

            coinItemListBinding.coin = coin

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        var coinItemListBinding: CoinItemListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.coin_item_list,
            parent,
            false
        )
        return CoinViewHolder(coinItemListBinding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin: Coin = coinlist[position]
        PreferenceUtil.user().set(coin)
        holder.bind(coin)
    }

    override fun getItemCount(): Int {
        return coinlist.size
    }
}