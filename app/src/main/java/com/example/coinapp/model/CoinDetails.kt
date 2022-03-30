package com.example.coinapp.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.coinapp.Utils.TypeConverter
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "Coin_details_tbl")
data class CoinDetails(
    val description: String,
    val development_status: String,
    val first_data_at: String,
    val hardware_wallet: Boolean,
    val hash_algorithm: String,
    @PrimaryKey
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val last_data_at: String,
    @Embedded
    val links: Links,
    val links_extended: List<LinksExtended>,
    val message: String,
    val name: String,
    val open_source: Boolean,
    val org_structure: String,
    val proof_type: String,
    val rank: Int,
    val started_at: String,
    val symbol: String,
    val tags: List<Tag>,
    val team: List<Team>,
    val type: String,
    @Embedded
    val whitepaper: Whitepaper?
):Parcelable