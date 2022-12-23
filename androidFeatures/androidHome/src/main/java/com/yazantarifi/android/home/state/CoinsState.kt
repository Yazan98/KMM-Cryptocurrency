package com.yazantarifi.android.home.state

import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.viewModels.props.CoinaState

sealed class CoinsState : CoinaState {
    object LoadingState: CoinsState()
    data class ListState(val payload: ArrayList<CoinModel>): CoinsState()
}
