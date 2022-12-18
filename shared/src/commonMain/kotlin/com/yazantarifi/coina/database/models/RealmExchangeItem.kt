package com.yazantarifi.coina.database.models

import com.yazantarifi.coina.models.CoinExchange
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class RealmExchangeItem : RealmObject {
    @PrimaryKey
    var exchangeId: String = ""

    var exchangeWebsite: String = ""
    var exchangeName: String = ""
    var exchangeSymbolsCount: Int = 0

    fun toCoinExchange(): CoinExchange {
        return CoinExchange(
            exchangeId,
            exchangeWebsite,
            exchangeName,
            exchangeSymbolsCount
        )
    }

}
