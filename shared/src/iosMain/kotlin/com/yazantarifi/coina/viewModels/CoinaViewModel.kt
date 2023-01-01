package com.yazantarifi.coina.viewModels

import com.yazantarifi.coina.viewModels.props.CoinaAction
import com.yazantarifi.coina.viewModels.props.CoinaState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class CoinaViewModel<Action: CoinaAction, State: CoinaState>: CoinaBaseViewModel<Action, State, State>() {

    override fun initializeViewModel() {
        super.initializeViewModel()
        if (state == null) {
            state = getInitialState()
        }

        state?.let {
            onNewStateTriggered(it)
        }
    }

    protected fun onAcceptNewState(newState: State) {
        scope.launch(Dispatchers.Default) {
            state = newState
            onNewStateTriggered(newState)
        }
    }

    override fun getCurrentState(): State? {
        return state
    }

}
