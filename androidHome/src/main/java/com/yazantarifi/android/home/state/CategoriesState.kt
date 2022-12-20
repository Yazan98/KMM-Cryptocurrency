package com.yazantarifi.android.home.state

import com.yazantarifi.coina.models.Category
import com.yazantarifi.coina.viewModels.props.CoinaState

sealed class CategoriesState: CoinaState {
    data class ListState(val data: ArrayList<Category>): CategoriesState()
    object LoadingState: CategoriesState()
}