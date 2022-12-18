package com.yazantarifi.coina.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazantarifi.coina.viewModels.props.CoinaAction
import com.yazantarifi.coina.viewModels.props.CoinaEither
import com.yazantarifi.coina.viewModels.props.CoinaSideEffect
import com.yazantarifi.coina.viewModels.props.CoinaState
import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

actual abstract class CoinaViewModel<Action: CoinaAction, State: CoinaState>: ViewModel() {

    actual val scope: CoroutineScope = viewModelScope
    actual val sideEffects: ArrayList<CoinaSideEffect<Action, *>> = ArrayList()
    actual val state: MutableStateFlow<State> by lazy { MutableStateFlow(getInitialState()) }
    actual var stateListener: CoinaStateListener<State>? = null
    actual val loadingState: MutableSharedFlow<Boolean> by lazy { MutableSharedFlow() }

    actual abstract fun onNewAction(action: Action)

    actual abstract fun getInitialState(): State

    actual fun onAcceptLoadingState(newState: Boolean) {
        scope.launch(Dispatchers.IO) {
            loadingState.emit(newState)
        }
    }

    actual fun onAcceptNewState(newState: State) {
        scope.launch(Dispatchers.IO) {
            state.update {
                newState
            }
        }
    }

    actual fun registerStateListener(targetStateListener: CoinaStateListener<State>) {
        this.stateListener = stateListener
        scope.launch(Dispatchers.IO) {
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

    override fun onCleared() {
        val state = clear()
        state.onStateTriggered {
            println("ViewModel State Cleared Success")
        }

        state.onSecondStateTriggered {
            println("ViewModel State Failed to Clear With Exception : ${it.message}")
        }
        super.onCleared()
    }

}
