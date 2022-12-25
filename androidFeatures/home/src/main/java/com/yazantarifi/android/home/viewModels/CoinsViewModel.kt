package com.yazantarifi.android.home.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.yazantarifi.android.home.action.CoinsAction
import com.yazantarifi.android.home.state.CoinsState
import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.useCases.GetCoinsUseCase
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCaseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val dataSource: CoinsDataSource,
    private val getCoinsUseCase: GetCoinsUseCase
    ): CoinaViewModel<CoinsAction, CoinsState>() {

    val coinsStateListener: MutableState<ArrayList<CoinModel>> by lazy { mutableStateOf(arrayListOf()) }
    val coinsLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    init {
        initializeViewModel()
    }

    override fun executeAction(action: CoinsAction) {
        when (action) {
            is CoinsAction.GetCoins -> getExchanges()
            is CoinsAction.GetCoinsByQuery -> getCoinsByQuery(action.query)
        }
    }


    private fun getCoinsByQuery(query: String) {
        coinsStateListener.value = dataSource.getCoinsBySearchQuery(query)
    }

    private fun getExchanges() {
        viewModelScope.launch {
            getCoinsUseCase(Unit)
        }
    }

    override fun getInitialState(): CoinsState {
        if (dataSource.isDataSourceEmpty()) return CoinsState.LoadingState
        return CoinsState.ListState(dataSource.getCoins())
    }

    override fun onExceptionListenerTriggered(key: String, value: Throwable) {

    }

    override fun getSupportedUseCases(): ArrayList<CoinaUseCaseType<Any>> {
        return ArrayList<CoinaUseCaseType<Any>>().apply {
            add(getCoinsUseCase as CoinaUseCaseType<Any>)
        }
    }

    override fun onListenerTriggered(key: String, value: CoinaApplicationState<Any>) {
        if (key == getCoinsUseCase.getUseCaseKey()) {
            value.handleResult({
                (it as? ArrayList<CoinModel>)?.let {
                    coinsStateListener.value = it
                }
            }, {

            }, {
                coinsLoadingState.value = it
            })
        }
    }

}