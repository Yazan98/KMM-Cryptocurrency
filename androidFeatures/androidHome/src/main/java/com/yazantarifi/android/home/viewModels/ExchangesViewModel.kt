package com.yazantarifi.android.home.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.yazantarifi.android.home.action.ExchangeAction
import com.yazantarifi.android.home.state.ExchangeState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.ExchangesDataSource
import com.yazantarifi.coina.models.ExchangeModel
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.listeners.CoinaLoadingStateListener
import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangesViewModel @Inject constructor(
    private val apiManager: ApplicationApiManager,
    private val database: ExchangesDataSource
): CoinaViewModel<ExchangeAction, ExchangeState>(), CoinaStateListener<ExchangeState>, CoinaLoadingStateListener {

    val screenLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    val screenContentState: MutableState<ArrayList<ExchangeModel>> by lazy { mutableStateOf(arrayListOf()) }
    init {
        registerStateListener(this)
        registerLoadingStateListener(this)
    }

    override fun onNewAction(action: ExchangeAction) {
        if (action is ExchangeAction.GetExchanges) {
            getExchangesList()
        }
    }

    private fun getExchangesList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (state.value is ExchangeState.LoadingState) {
                onLoadingStateChanged(true)
                apiManager.getExchanges(database) {
                    onLoadingStateChanged(false)
                    it.handleResult({
                        it?.let {
                            onAcceptNewState(ExchangeState.ListState(it))
                        }
                    }, {

                    })
                }
            }
        }
    }

    override fun getInitialState(): ExchangeState {
        if (database.isDataSourceEmpty()) return ExchangeState.LoadingState
        return ExchangeState.ListState(database.getExchanges())
    }

    override fun onLoadingStateChanged(newState: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            screenLoadingState.value = newState
        }
    }

    override fun onUpdateState(newState: ExchangeState) {
        if (newState is ExchangeState.ListState) {
            screenContentState.value = newState.data
        }
    }

}