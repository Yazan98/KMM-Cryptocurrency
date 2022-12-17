package com.yazantarifi.coina.api.requests

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.models.CoinImage

interface ApplicationApiManagerImplementation {

    suspend fun getCoinsImages(onNewStateTriggered: (CoinaApplicationState<ArrayList<CoinImage>>) -> Unit)

}