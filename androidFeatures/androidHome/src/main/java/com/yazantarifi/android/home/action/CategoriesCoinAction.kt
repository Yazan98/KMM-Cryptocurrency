package com.yazantarifi.android.home.action

import com.yazantarifi.coina.viewModels.props.CoinaAction

sealed class CategoriesCoinAction: CoinaAction {
    data class GetCategoryCoin(val categoryName: String): CategoriesCoinAction()
}
