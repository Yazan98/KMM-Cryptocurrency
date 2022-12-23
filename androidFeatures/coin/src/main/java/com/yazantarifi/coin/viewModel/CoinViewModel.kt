package com.yazantarifi.coin.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.models.CoinInformation
import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.listeners.CoinaLoadingStateListener
import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val apiManager: ApplicationApiManager
): CoinaViewModel<CoinAction, CoinState>(), CoinaStateListener<CoinState>, CoinaLoadingStateListener {

    val screenLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    val screenContentState: MutableState<CoinInformation?> by lazy { mutableStateOf(null) }
    init {
        registerStateListener(this)
        registerLoadingStateListener(this)
    }

    override fun onNewAction(action: CoinAction) {
        if (action is CoinAction.GetCoinByKey) {
            getCoinInformationByKey(action.id)
        }
    }

    private fun getCoinInformationByKey(id: String) {
        scope.launch(Dispatchers.IO) {
            onAcceptLoadingState(true)
            apiManager.getCoinInformation(id) {
                it.handleResult({
                    onAcceptLoadingState(false)
                    it?.let {
                        onAcceptNewState(CoinState.InfoState(it))
                    }
                }, {
                    onAcceptLoadingState(false)
                })
            }
        }
    }

    override fun getInitialState(): CoinState {
        return CoinState.LoadingState
    }

    override fun onLoadingStateChanged(newState: Boolean) {
        scope.launch(Dispatchers.Main) {
            screenLoadingState.value = newState
        }
    }

    override fun onUpdateState(newState: CoinState) {
        if (newState is CoinState.InfoState) {
            screenContentState.value = newState.info
        }
    }
}