package com.yazantarifi.android.home.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.yazantarifi.android.home.action.ExchangeAction
import com.yazantarifi.android.home.state.ExchangeState
import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.database.ExchangesDataSource
import com.yazantarifi.coina.models.ExchangeModel
import com.yazantarifi.coina.useCases.GetExchangesUseCase
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCaseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangesViewModel @Inject constructor(
    private val getExchangesUseCase: GetExchangesUseCase,
    private val database: ExchangesDataSource
): CoinaViewModel<ExchangeAction, ExchangeState>() {

    val screenLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    val screenContentState: MutableState<ArrayList<ExchangeModel>> by lazy { mutableStateOf(arrayListOf()) }
    init {
        initializeViewModel()
    }

    override fun executeAction(action: ExchangeAction) {
        if (action is ExchangeAction.GetExchanges) {
            getExchangesList()
        }
    }

    private fun getExchangesList() {
        viewModelScope.launch(Dispatchers.IO) {
            getExchangesUseCase(Unit)
        }
    }

    override fun getInitialState(): ExchangeState {
        if (database.isDataSourceEmpty()) return ExchangeState.LoadingState
        return ExchangeState.ListState(database.getExchanges())
    }

    override fun onListenerTriggered(key: String, value: CoinaApplicationState<Any>) {
        if (key == getExchangesUseCase.getUseCaseKey()) {
            value.handleResult({
                (it as? ArrayList<ExchangeModel>)?.let {
                    screenContentState.value = it
                }
            }, {

            }, {
                screenLoadingState.value = it
            })
        }
    }

    override fun getSupportedUseCases(): ArrayList<CoinaUseCaseType<Any>> {
        return ArrayList<CoinaUseCaseType<Any>>().apply {
            add(getExchangesUseCase as CoinaUseCaseType<Any>)
        }
    }

}