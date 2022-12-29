package com.yazantarifi.android.home.state

import com.yazantarifi.coina.models.CategoryModel
import com.yazantarifi.coina.viewModels.props.CoinaState

sealed class CategoriesState: CoinaState {
    data class ListState(val data: ArrayList<CategoryModel>): CategoriesState()
    object LoadingState: CategoriesState()
}