package com.yazantarifi.android.home.action

import com.yazantarifi.coina.viewModels.props.CoinaAction

sealed class CategoriesAction : CoinaAction {
    object GetCategories: CategoriesAction()
}
