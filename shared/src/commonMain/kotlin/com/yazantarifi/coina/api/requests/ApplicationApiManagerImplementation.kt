package com.yazantarifi.coina.api.requests

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.database.CategoriesDataSource
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.database.ExchangesDataSource
import com.yazantarifi.coina.models.CategoryModel
import com.yazantarifi.coina.models.CoinInformation
import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.models.ExchangeModel

interface ApplicationApiManagerImplementation {

    suspend fun getCoins(database: CoinsDataSource, onNewStateTriggered: (CoinaApplicationState<ArrayList<CoinModel>>) -> Unit)

    suspend fun getCoinsByCategoryName(categoryName: String, onNewStateTriggered: (CoinaApplicationState<ArrayList<CoinModel>>) -> Unit)

    suspend fun getCategories(database: CategoriesDataSource, onNewStateTriggered: (CoinaApplicationState<ArrayList<CategoryModel>>) -> Unit)

    suspend fun getCoinInformation(key: String, onNewStateTriggered: (CoinaApplicationState<CoinInformation>) -> Unit)

    suspend fun getExchanges(database: ExchangesDataSource, onNewStateTriggered: (CoinaApplicationState<ArrayList<ExchangeModel>>) -> Unit)

}
