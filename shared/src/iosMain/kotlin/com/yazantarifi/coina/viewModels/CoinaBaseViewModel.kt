package com.yazantarifi.coina.viewModels

import com.yazantarifi.coina.CoinaApplicationState
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


actual abstract class CoinaBaseViewModel<Action: CoinaAction, State: CoinaState, StateType> {

    private var stateListener: CoinaStateListener<State>? = null
    actual val sideEffects: ArrayList<CoinaSideEffect<Action, *>> = ArrayList()
    actual var state: StateType? = null
    actual val scope: CoroutineScope = object: CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Job() + Dispatchers.Default
    }

    actual open fun initializeViewModel() {
        getSupportedUseCases().forEach { useCase ->
            scope.launch(Dispatchers.Default) {
                useCase.getChannelListener().consumeEach {
                    scope.launch(Dispatchers.Main) {
                        onListenerTriggered(useCase.getUseCaseKey(), it)
                    }
                }
            }
        }

        onNewStateTriggered(getInitialState())
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
            getSupportedUseCases().forEach {
                it.clear()
            }

            CoinaEither(true, null)
        } catch (ex: Exception) {
            CoinaEither(null, ex)
        }
    }

    protected fun addStateListener(instance: CoinaStateListener<State>) {
        if (stateListener == null) {
            stateListener = instance
        }
    }

    protected fun getStateListenerInstance(): CoinaStateListener<State>? {
        return this.stateListener
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
