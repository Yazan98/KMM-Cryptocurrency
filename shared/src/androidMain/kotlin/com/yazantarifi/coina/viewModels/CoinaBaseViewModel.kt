package com.yazantarifi.coina.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.viewModels.props.CoinaAction
import com.yazantarifi.coina.viewModels.props.CoinaEither
import com.yazantarifi.coina.viewModels.props.CoinaSideEffect
import com.yazantarifi.coina.viewModels.props.CoinaState
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCaseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

actual abstract class CoinaBaseViewModel<Action: CoinaAction, State: CoinaState, StateType>: ViewModel() {

    actual val scope: CoroutineScope = viewModelScope
    actual val sideEffects: ArrayList<CoinaSideEffect<Action, *>> = ArrayList()
    actual var state: StateType? = null

    actual open fun initializeViewModel() {
        getSupportedUseCases().forEach { useCase ->
            scope.launch(Dispatchers.IO) {
                useCase.getChannelListener().consumeEach {
                    scope.launch(Dispatchers.Main) {
                        onListenerTriggered(useCase.getUseCaseKey(), it)
                    }
                }
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
            getSupportedUseCases().forEach {
                it.clear()
            }

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

    actual open fun getCurrentState(): State? {
        return this.state as? State
    }

    open fun onNewStateTriggered(newState: State) = Unit

    actual abstract fun executeAction(action: Action)

    actual abstract fun getInitialState(): State

    actual abstract fun onListenerTriggered(key: String, value: CoinaApplicationState<Any>)

    actual abstract fun getSupportedUseCases(): ArrayList<CoinaUseCaseType<Any>>

}
