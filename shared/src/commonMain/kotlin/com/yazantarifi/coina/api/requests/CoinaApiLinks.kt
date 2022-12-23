package com.yazantarifi.coina.api.requests

object CoinaApiLinks {

    const val COINS_MARKETPLACE = "coins/markets?vs_currency=usd&order=market_cap_desc&per_page=250&page=1&sparkline=false"
    const val COINS_LIST_CATEGORIES = "coins/categories"
    const val COINS_LIST_EXCHANGES = "exchanges?per_page=250&page=1"
    const val COIN_INFO = "coins/{key}?localization=false&tickers=false&market_data=true&community_data=true&developer_data=true&sparkline=true"

}