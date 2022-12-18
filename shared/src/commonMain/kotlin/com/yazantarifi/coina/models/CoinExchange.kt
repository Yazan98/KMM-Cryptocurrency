package com.yazantarifi.coina.models

import com.yazantarifi.coina.database.models.RealmExchangeItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinExchange(
    @SerialName("exchange_id") var id: String? = "",
    @SerialName("website") var website: String? = "",
    @SerialName("name") var name: String? = "",
    @SerialName("data_symbols_count") var symbolsCount: Int? = 0,
) {

    fun toRealmExchange(): RealmExchangeItem {
        return RealmExchangeItem().apply {
            exchangeWebsite = website ?: ""
            exchangeId = id ?: ""
            exchangeName = name ?: ""
            exchangeSymbolsCount = symbolsCount ?: 0
        }
    }

}