package com.yazantarifi.coina.viewModels

import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import com.yazantarifi.coina.viewModels.props.CoinaAction
import com.yazantarifi.coina.viewModels.props.CoinaEither
import com.yazantarifi.coina.viewModels.props.CoinaSideEffect
import com.yazantarifi.coina.viewModels.props.CoinaState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


actual abstract class CoinaViewModel<Action: CoinaAction, State: CoinaState> {


    actual val sideEffects: ArrayList<CoinaSideEffect<Action, *>> = ArrayList()
    actual val state: MutableStateFlow<State> by lazy { MutableStateFlow(getInitialState()) }
    actual var stateListener: CoinaStateListener<State>? = null
    actual val loadingState: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }
    actual val scope: CoroutineScope = object: CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Job() + Dispatchers.Default
    }

    actual abstract fun onNewAction(action: Action)

    actual abstract fun getInitialState(): State

    actual fun onAcceptLoadingState(newState: Boolean) {
        scope.launch(Dispatchers.Default) {
            loadingState.emit(newState)
        }
    }

    actual fun onAcceptNewState(newState: State) {
        scope.launch(Dispatchers.Default) {
            state.update {
                newState
            }
        }
    }

    actual fun registerStateListener(targetStateListener: CoinaStateListener<State>) {
        this.stateListener = stateListener
        scope.launch(Dispatchers.Default) {
            state.stateIn(scope).collect {
                stateListener?.onUpdateState(it)
            }
        }
    }

    actual fun onTriggerSideEffectAction(newAction: Action, sideEffectKey: String) {
        this.sideEffects.forEach {
            if (it.getSideEffectKey().equals(sideEffectKey)) {
                it.onExecuteSideEffect(newAction)
            }
        }
    }

    actual open fun registerSideEffect(sideEffect: CoinaSideEffect<Action, *>) {
        val isSideEffectAlreadyExists = sideEffects.filter { it.getSideEffectKey().equals(sideEffect.getSideEffectKey()) }.size > 0
        if (!isSideEffectAlreadyExists) {
            this.sideEffects.add(sideEffect)
        }
    }

    actual fun clear(): CoinaEither<Boolean, Exception> {
        return try {
            scope.cancel()
            sideEffects.clear()
            stateListener = null
            CoinaEither(true, null)
        } catch (ex: Exception) {
            CoinaEither(null, ex)
        }
    }

}
