package com.example.coinapp.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinksExtended(
    @Embedded
    val stats: Stats,
    val type: String,
    val url: String
):Parcelable