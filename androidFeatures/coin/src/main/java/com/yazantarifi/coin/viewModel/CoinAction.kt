package com.yazantarifi.coin.viewModel

import com.yazantarifi.coina.viewModels.props.CoinaAction

sealed class CoinAction: CoinaAction {
    data class GetCoinByKey(val id: String): CoinAction()
}