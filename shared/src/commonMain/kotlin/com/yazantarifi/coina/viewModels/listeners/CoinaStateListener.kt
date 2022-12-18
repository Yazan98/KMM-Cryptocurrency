package com.yazantarifi.coina.viewModels.listeners

import com.yazantarifi.coina.viewModels.props.CoinaState

interface CoinaStateListener<State: CoinaState> {

    fun onUpdateState(newState: State)

}