package com.yazantarifi.coina.api.requests

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.database.CoinExchangesDataSource
import com.yazantarifi.coina.database.CoinImagesDatabase
import com.yazantarifi.coina.models.CoinExchange
import com.yazantarifi.coina.models.CoinImage

interface ApplicationApiManagerImplementation {

    suspend fun getCoinsImages(database: CoinImagesDatabase, onNewStateTriggered: (CoinaApplicationState<ArrayList<CoinImage>>) -> Unit)

    suspend fun getExchangesList(database: CoinExchangesDataSource, onNewStateTriggered: (CoinaApplicationState<ArrayList<CoinExchange>>) -> Unit)

}
