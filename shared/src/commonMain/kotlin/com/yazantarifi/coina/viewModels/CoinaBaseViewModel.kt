package com.yazantarifi.coina.viewModels

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.viewModels.props.CoinaAction
import com.yazantarifi.coina.viewModels.props.CoinaEither
import com.yazantarifi.coina.viewModels.props.CoinaSideEffect
import com.yazantarifi.coina.viewModels.props.CoinaState
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCaseType
import kotlinx.coroutines.CoroutineScope

expect abstract class CoinaBaseViewModel<Action: CoinaAction, State: CoinaState, StateType> {

    /**
     * The Current Coroutine Context Used inside ViewModel
     * Needed Because ViewModelScope By Default Working on Main Thread
     */
    val scope: CoroutineScope
    val sideEffects: ArrayList<CoinaSideEffect<Action, *>>
    var state: StateType?

    /**
     * Initialize the ViewModel Streaming Listeners for Each UseCase
     * Now We Run Consumers to Collect the Data From Each Channel and Return it to Sub ViewModel
     */
    fun initializeViewModel()

    fun registerSideEffect(sideEffect: CoinaSideEffect<Action, *>)

    fun onTriggerSideEffectAction(newAction: Action, sideEffectKey: String)

    /**
     * Any Event in ViewModel Should Start from Here
     * Here is the Start Point in ViewModel to Submit Actions to ViewModel from the View itself
     */
    abstract fun executeAction(action: Action)

    abstract fun getInitialState(): State

    /**
     * This Method is Triggered When UseCases Submit the State or Result To Return it Back to ViewModel
     * Like Loading, Error, Data
     */
    abstract fun onListenerTriggered(key: String, value: CoinaApplicationState<Any>)

    /**
     * Save The Current UseCases Inside the ViewModel to Clear Them Automaticlly When ViewModel Destroyed
     */
    abstract fun getSupportedUseCases(): ArrayList<CoinaUseCaseType<Any>>

    /**
     * Remove The Instances and listeners when Destroy the ViewModel
     */
    fun clear(): CoinaEither<Boolean, Exception>

    fun getCurrentState(): State?

}