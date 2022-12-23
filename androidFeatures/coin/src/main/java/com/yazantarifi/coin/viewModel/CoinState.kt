package com.yazantarifi.coin.viewModel

import com.yazantarifi.coina.models.CoinInformation
import com.yazantarifi.coina.viewModels.props.CoinaState

sealed class CoinState: CoinaState {
    object LoadingState: CoinState()
    data class InfoState(val info: CoinInformation): CoinState()
}
