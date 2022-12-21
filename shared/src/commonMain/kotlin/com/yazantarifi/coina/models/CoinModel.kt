package com.yazantarifi.coina.models

import com.yazantarifi.coina.database.models.RealmCoinModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.round

@Serializable
data class CoinModel(
    @SerialName("id") val id: String? = "",
    @SerialName("symbol") val symbol: String? = "",
    @SerialName("name") val name: String? = "",
    @SerialName("image") val image: String? = "",
    @SerialName("current_price") val price: Double? = 0.0,
    @SerialName("market_cap") val marketGap: Long? = 0,
    @SerialName("market_cap_rank") val marketGapRank: Int? = 0,
    @SerialName("price_change_percentage_24h") val percentChange: Double? = 0.0,
) {
    companion object {
        fun toRealmCoinModel(model: CoinModel): RealmCoinModel {
            return RealmCoinModel().apply {
                id = model.id ?: ""
                symbol = model.symbol ?: ""
                name = model.name ?: ""
                image = model.image ?: ""
                price = model.price ?: 0.0
                marketGap = model.marketGap ?: 0
                marketGapRank = model.marketGapRank ?: 0
                percentChange = model.percentChange ?: 0.0
            }
        }
    }

    fun getPriceChangeText(): String {
        val number3digits:Double = round((percentChange ?: 0.0) * 1000.0) / 1000.0
        return (round(number3digits * 100.0) / 100.0).toString()
    }

}
