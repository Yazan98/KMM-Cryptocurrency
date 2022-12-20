package com.yazantarifi.coina.api.requests

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.database.CategoriesDataSource
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.models.Category
import com.yazantarifi.coina.models.CoinModel

interface ApplicationApiManagerImplementation {

    suspend fun getCoins(database: CoinsDataSource, onNewStateTriggered: (CoinaApplicationState<ArrayList<CoinModel>>) -> Unit)

    suspend fun getCategories(database: CategoriesDataSource, onNewStateTriggered: (CoinaApplicationState<ArrayList<Category>>) -> Unit)

}
