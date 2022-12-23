package com.yazantarifi.android.auth.viewModel

import com.yazantarifi.coina.viewModels.props.CoinaAction

sealed class AuthAction: CoinaAction {
    object LoginAction: AuthAction()
}
