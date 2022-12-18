package com.yazantarifi.android.home.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.yazantarifi.android.home.action.ExchangeAction
import com.yazantarifi.android.home.state.ExchangeState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.CoinExchangesDataSource
import com.yazantarifi.coina.models.CoinExchange
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.listeners.CoinaLoadingStateListener
import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangesViewModel @Inject constructor(
    private val dataSource: CoinExchangesDataSource,
    private val apiManager: ApplicationApiManager,
    ): CoinaViewModel<ExchangeAction, ExchangeState>(), CoinaStateListener<ExchangeState>, CoinaLoadingStateListener {

    val exchangesStateListener: MutableState<ArrayList<CoinExchange>> by lazy { mutableStateOf(arrayListOf()) }
    val exchangesLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    init {
        registerStateListener(this)
        registerLoadingStateListener(this)
    }

    override fun onNewAction(action: ExchangeAction) {
        when (action) {
            is ExchangeAction.GetExchanges -> getExchanges()
        }
    }

    private fun getExchanges() {
        viewModelScope.launch {
            apiManager.getExchangesList(dataSource) {
                it.handleResult({
                    onAcceptLoadingState(false)
                    it?.let {
                        exchangesStateListener.value = it
                    }
                }, {
                    onAcceptLoadingState(false)
                })
            }
        }
    }

    override fun getInitialState(): ExchangeState {
        if (dataSource.isExchangesEmpty()) return ExchangeState.LoadingState
        return ExchangeState.ListState(dataSource.getImages())
    }

    override fun onLoadingStateChanged(newState: Boolean) {
        exchangesLoadingState.value = newState
    }

    override fun onUpdateState(newState: ExchangeState) {
        if (newState is ExchangeState.ListState) {
            exchangesStateListener.value = newState.payload
        } else {
            exchangesLoadingState.value = true
        }
    }
}