package com.yazantarifi.android.home.action

import com.yazantarifi.coina.viewModels.props.CoinaAction

sealed class CoinsAction : CoinaAction {
    object GetCoins: CoinsAction()
    data class GetCoinsByQuery(val query: String): CoinsAction()
}
