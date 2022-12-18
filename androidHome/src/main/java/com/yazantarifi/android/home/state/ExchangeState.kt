package com.yazantarifi.android.home.state

import com.yazantarifi.coina.models.CoinExchange
import com.yazantarifi.coina.viewModels.props.CoinaState

sealed class ExchangeState : CoinaState {
    object LoadingState: ExchangeState()
    data class ListState(val payload: ArrayList<CoinExchange>): ExchangeState()
}
