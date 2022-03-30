package com.example.coinapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag(
    val coin_counter: Int,
    val ico_counter: Int,
    val id: String,
    val name: String
): Parcelable