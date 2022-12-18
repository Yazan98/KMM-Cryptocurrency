package com.yazantarifi.android.home.state

import com.yazantarifi.coina.models.CoinImage
import com.yazantarifi.coina.viewModels.props.CoinaState

sealed class CoinsState : CoinaState {
    data class ListState(val coins: ArrayList<CoinImage>): CoinsState()
    object EmptyState: CoinsState()
}