package com.yazantarifi.coina.viewModels

import com.yazantarifi.coina.viewModels.props.CoinaAction
import com.yazantarifi.coina.viewModels.props.CoinaEither
import com.yazantarifi.coina.viewModels.props.CoinaSideEffect
import com.yazantarifi.coina.viewModels.props.CoinaState
import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

expect abstract class CoinaViewModel<Action: CoinaAction, State: CoinaState> {

    val scope: CoroutineScope
    val sideEffects: ArrayList<CoinaSideEffect<Action, *>>
    val state: MutableStateFlow<State>
    val loadingState: MutableSharedFlow<Boolean>
    var stateListener: CoinaStateListener<State>?

    fun onAcceptLoadingState(newState: Boolean)

    fun onAcceptNewState(newState: State)

    fun registerStateListener(targetStateListener: CoinaStateListener<State>)

    fun registerSideEffect(sideEffect: CoinaSideEffect<Action, *>)

    fun onTriggerSideEffectAction(newAction: Action, sideEffectKey: String)

    abstract fun onNewAction(action: Action)

    abstract fun getInitialState(): State

    fun clear(): CoinaEither<Boolean, Exception>

}