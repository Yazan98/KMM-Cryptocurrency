package com.yazantarifi.coina.models

data class CoinInfoItem(
    val type: Int,
    val title: String,
    val value: String,
    val image: String? = null,
    val chart: ArrayList<Double>? = null
) {
    companion object {
        const val TYPE_TITLE = 1
        const val TYPE_CHART = 2

        fun getScreenItems(coinInformation: CoinInformation?): ArrayList<CoinInfoItem> {
            val items = ArrayList<CoinInfoItem>()

            coinInformation?.let {
                items.add(CoinInfoItem(TYPE_TITLE, coinInformation.name ?: "", coinInformation.symbol ?: "", coinInformation.image?.large))
                items.add(CoinInfoItem(TYPE_CHART, coinInformation.name ?: "", coinInformation.symbol ?: "", coinInformation.image?.large, coinInformation.marketData?.value?.value))
            }

            return items
        }
    }
}
