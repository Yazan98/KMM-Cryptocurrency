package com.yazantarifi.android.home.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.yazantarifi.android.home.action.CoinsAction
import com.yazantarifi.android.home.state.CoinsState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.listeners.CoinaLoadingStateListener
import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val dataSource: CoinsDataSource,
    private val apiManager: ApplicationApiManager,
    ): CoinaViewModel<CoinsAction, CoinsState>(), CoinaStateListener<CoinsState>, CoinaLoadingStateListener {

    val coinsStateListener: MutableState<ArrayList<CoinModel>> by lazy { mutableStateOf(arrayListOf()) }
    val coinsLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    init {
        registerStateListener(this)
        registerLoadingStateListener(this)
    }

    override fun onNewAction(action: CoinsAction) {
        when (action) {
            is CoinsAction.GetCoins -> getExchanges()
        }
    }

    private fun getExchanges() {
        viewModelScope.launch {
            apiManager.getCoins(dataSource) {
                it.handleResult({
                    onAcceptLoadingState(false)
                    it?.let {
                        coinsStateListener.value = it
                    }
                }, {
                    onAcceptLoadingState(false)
                })
            }
        }
    }

    override fun getInitialState(): CoinsState {
        if (dataSource.isDataSourceEmpty()) return CoinsState.LoadingState
        return CoinsState.ListState(dataSource.getCoins())
    }

    override fun onLoadingStateChanged(newState: Boolean) {
        coinsLoadingState.value = newState
    }

    override fun onUpdateState(newState: CoinsState) {
        if (newState is CoinsState.ListState) {
            coinsStateListener.value = newState.payload
        } else {
            coinsLoadingState.value = true
        }
    }
}