package com.yazantarifi.coin.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.models.CoinInformation
import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.useCases.CoinInfoUseCase
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCaseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getCoinInfoUseCase: CoinInfoUseCase
): CoinaViewModel<CoinAction, CoinState>() {

    val screenLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    val screenContentState: MutableState<CoinInformation?> by lazy { mutableStateOf(null) }
    init {
        initViewModel()
    }

    override fun executeAction(action: CoinAction) {
        if (action is CoinAction.GetCoinByKey) {
            getCoinInformationByKey(action.id)
        }
    }

    private fun getCoinInformationByKey(id: String) {
        scope.launch(Dispatchers.IO) {
            getCoinInfoUseCase(CoinInfoUseCase.Args(id))
        }
    }

    override fun getInitialState(): CoinState {
        return CoinState.LoadingState
    }

    override fun onListenerTriggered(key: String, value: CoinaApplicationState<Any>) {
        if (key == CoinInfoUseCase.KEY) {
            value.handleResult({
                if (it is CoinInformation) {
                    screenContentState.value = it
                }
            }, {

            }, {
                screenLoadingState.value = it
            })
        }
    }

    override fun onExceptionListenerTriggered(key: String, value: Throwable) {
        println("Error :: Key : $key - Value : ${value.message}")
    }

    override fun getSupportedUseCases(): ArrayList<CoinaUseCaseType<Any>> {
        return ArrayList<CoinaUseCaseType<Any>>().apply {
            add(getCoinInfoUseCase as CoinaUseCaseType<Any>)
        }
    }

}