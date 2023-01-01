package com.yazantarifi.coina.viewModels

import com.yazantarifi.coina.viewModels.props.CoinaAction
import com.yazantarifi.coina.viewModels.props.CoinaState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class CoinaViewModel<Action: CoinaAction, State: CoinaState>: CoinaBaseViewModel<Action, State, MutableStateFlow<State>>() {

    override fun initializeViewModel() {
        super.initializeViewModel()
        if (state == null) {
            state = MutableStateFlow(getInitialState())
        }

        scope.launch(Dispatchers.IO) {
            state?.collect {
                onNewStateTriggered(it)
            }
        }
    }

    protected fun onAcceptNewState(newState: State) {
        scope.launch(Dispatchers.IO) {
            state?.update { newState }
        }
    }

    override fun getCurrentState(): State? {
        return state?.value
    }

}
