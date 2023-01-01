package com.yazantarifi.coina.viewModels

import com.yazantarifi.coina.viewModels.props.CoinaState

interface CoinaStateListener<State: CoinaState> {

    fun onStateChanged(newState: CoinaState)

    fun onLoadingStateChanged(newState: Boolean)

}
