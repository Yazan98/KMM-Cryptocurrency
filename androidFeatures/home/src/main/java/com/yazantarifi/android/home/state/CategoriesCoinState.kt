package com.yazantarifi.android.home.state

import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.viewModels.props.CoinaState

sealed class CategoriesCoinState: CoinaState {
    object LoadingState: CategoriesCoinState()
    data class ListState(val coins: ArrayList<CoinModel>): CategoriesCoinState()
}
