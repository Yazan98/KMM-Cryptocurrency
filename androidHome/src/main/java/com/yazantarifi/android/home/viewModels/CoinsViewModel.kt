package com.yazantarifi.android.home.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.yazantarifi.android.home.action.CoinsAction
import com.yazantarifi.android.home.state.CoinsState
import com.yazantarifi.coina.database.CoinImagesDatabase
import com.yazantarifi.coina.models.CoinImage
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val database: CoinImagesDatabase
): CoinaViewModel<CoinsAction, CoinsState>(), CoinaStateListener<CoinsState> {

    val coinsState: MutableState<ArrayList<CoinImage>> by lazy { mutableStateOf(arrayListOf()) }
    init {
        registerStateListener(this)
    }

    override fun onNewAction(action: CoinsAction) {
        if (action is CoinsAction.GetCoins) {
            getCachedCoins()
        }
    }

    private fun getCachedCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            onAcceptNewState(CoinsState.ListState(database.getImages()))
        }
    }

    override fun getInitialState(): CoinsState {
        return CoinsState.EmptyState
    }

    override fun onUpdateState(newState: CoinsState) {
        if (newState is CoinsState.ListState) {
            coinsState.value = newState.coins
        }
    }
}