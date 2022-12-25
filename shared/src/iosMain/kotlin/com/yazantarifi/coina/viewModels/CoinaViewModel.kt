package com.yazantarifi.coina.viewModels

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.viewModels.listeners.CoinaLoadingStateListener
import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import com.yazantarifi.coina.viewModels.props.CoinaAction
import com.yazantarifi.coina.viewModels.props.CoinaEither
import com.yazantarifi.coina.viewModels.props.CoinaSideEffect
import com.yazantarifi.coina.viewModels.props.CoinaState
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCaseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


actual abstract class CoinaViewModel<Action: CoinaAction, State: CoinaState> {

    actual val sideEffects: ArrayList<CoinaSideEffect<Action, *>> = ArrayList()
    actual val state: MutableStateFlow<State> by lazy { MutableStateFlow(getInitialState()) }
    actual val scope: CoroutineScope = object: CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Job() + Dispatchers.Default
    }

    actual fun onAcceptNewState(newState: State) {
        scope.launch(Dispatchers.Default) {
            state.update {
                newState
            }
        }
    }

    actual fun initViewModel() {
        getSupportedUseCases().forEach { useCase ->
            scope.launch(Dispatchers.Default) {
                useCase.getChannelListener().consumeEach {
                    scope.launch(Dispatchers.Main) {
                        onListenerTriggered(useCase.getUseCaseKey(), it)
                    }
                }

                useCase.getErrorChannelListener().consumeEach {
                    scope.launch(Dispatchers.Main) {
                        onExceptionListenerTriggered(useCase.getUseCaseKey(), it)
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

    actual abstract fun executeAction(action: Action)

    actual abstract fun getInitialState(): State

    actual abstract fun onExceptionListenerTriggered(key: String, value: Throwable)

    actual abstract fun onListenerTriggered(key: String, value: CoinaApplicationState<Any>)

    actual abstract fun getSupportedUseCases(): ArrayList<CoinaUseCaseType<Any>>

}
