package com.yazantarifi.coina.database.models

import com.yazantarifi.coina.models.CoinModel
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class RealmCoinModel : RealmObject {
    @PrimaryKey
    var id: String = ""

    var symbol: String? = ""
    var name: String? = ""
    var image: String? = ""
    var price: Double? = 0.0
    var marketGap: Long? = 0
    var marketGapRank: Int? = 0

    companion object {
        const val MARKET_GAP_RANK = "marketGapRank"

        fun toCoinModel(model: RealmCoinModel): CoinModel {
            return CoinModel(
                model.id,
                model.symbol,
                model.name,
                model.image,
                model.price,
                model.marketGap,
                model.marketGapRank
            )
        }
    }

}