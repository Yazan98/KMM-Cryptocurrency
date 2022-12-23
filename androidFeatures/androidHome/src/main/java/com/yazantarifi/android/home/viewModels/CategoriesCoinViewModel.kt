package com.yazantarifi.android.home.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.yazantarifi.android.home.action.CategoriesCoinAction
import com.yazantarifi.android.home.state.CategoriesCoinState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.listeners.CoinaLoadingStateListener
import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesCoinViewModel @Inject constructor(
    private val apiManager: ApplicationApiManager
): CoinaViewModel<CategoriesCoinAction, CategoriesCoinState>(), CoinaStateListener<CategoriesCoinState>, CoinaLoadingStateListener {

    val screenLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    val screenContentState: MutableState<ArrayList<CoinModel>> by lazy { mutableStateOf(arrayListOf()) }
    init {
        registerStateListener(this)
        registerLoadingStateListener(this)
    }

    override fun onNewAction(action: CategoriesCoinAction) {
        if (action is CategoriesCoinAction.GetCategoryCoin) {
            getCoinsByCategoryName(action.categoryName)
        }
    }

    private fun getCoinsByCategoryName(categoryName: String) {
        scope.launch(Dispatchers.IO) {
            onLoadingStateChanged(true)
            apiManager.getCoinsByCategoryName(categoryName) {
                it.handleResult({
                    onLoadingStateChanged(false)
                    it?.let {
                        onAcceptNewState(CategoriesCoinState.ListState(it))
                    }
                }, {
                    onLoadingStateChanged(false)
                })
            }
        }
    }

    override fun getInitialState(): CategoriesCoinState {
        return CategoriesCoinState.LoadingState
    }

    override fun onLoadingStateChanged(newState: Boolean) {
        scope.launch(Dispatchers.Main) {
            screenLoadingState.value = newState
        }
    }

    override fun onUpdateState(newState: CategoriesCoinState) {
        if (newState is CategoriesCoinState.ListState) {
            screenContentState.value = newState.coins
        }
    }

}