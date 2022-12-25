package com.yazantarifi.android.home.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.yazantarifi.android.home.action.CategoriesAction
import com.yazantarifi.android.home.state.CategoriesState
import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.database.CategoriesDataSource
import com.yazantarifi.coina.models.Category
import com.yazantarifi.coina.useCases.GetCategoriesUseCase
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCaseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val categoriesDataSource: CategoriesDataSource
): CoinaViewModel<CategoriesAction, CategoriesState>() {

    val screenLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    val screenContentState: MutableState<ArrayList<Category>> by lazy { mutableStateOf(arrayListOf()) }
    init {
        initializeViewModel()
    }

    override fun executeAction(action: CategoriesAction) {
        if (action is CategoriesAction.GetCategories) {
            getCategoriesList()
        }
    }

    private fun getCategoriesList() {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase(Unit)
        }
    }

    override fun getInitialState(): CategoriesState {
        if (categoriesDataSource.isDataSourceEmpty()) return CategoriesState.LoadingState
        return CategoriesState.ListState(categoriesDataSource.getCategories())
    }

    override fun onListenerTriggered(key: String, value: CoinaApplicationState<Any>) {
        if (key == GetCategoriesUseCase.KEY) {
            value.handleResult({
                (it as? ArrayList<Category>)?.let {
                    screenContentState.value = it
                }
            }, {

            }, {
                screenLoadingState.value = it
            })
        }
    }

    override fun onExceptionListenerTriggered(key: String, value: Throwable) {

    }

    override fun getSupportedUseCases(): ArrayList<CoinaUseCaseType<Any>> {
        return ArrayList<CoinaUseCaseType<Any>>().apply {
            add(getCategoriesUseCase as CoinaUseCaseType<Any>)
        }
    }

}