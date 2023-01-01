package com.yazantarifi.coina.viewModels

import com.yazantarifi.coina.viewModels.props.CoinaState

interface CoinaStateListenerHandler<State: CoinaState> {

    fun onStateChanged(newState: State)

    fun onLoadingStateChanged(newState: Boolean)

}
