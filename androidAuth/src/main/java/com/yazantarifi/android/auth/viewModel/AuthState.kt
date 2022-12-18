package com.yazantarifi.android.auth.viewModel

import com.yazantarifi.coina.viewModels.props.CoinaState

sealed class AuthState: CoinaState {
    object SuccessState: AuthState()
    object EmptyState: AuthState()
}
