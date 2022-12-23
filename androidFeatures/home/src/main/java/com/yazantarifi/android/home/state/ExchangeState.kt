package com.yazantarifi.android.home.state

import com.yazantarifi.coina.models.ExchangeModel
import com.yazantarifi.coina.viewModels.props.CoinaState

sealed class ExchangeState: CoinaState {
    object LoadingState: ExchangeState()
    data class ListState(val data: ArrayList<ExchangeModel>): ExchangeState()
}
