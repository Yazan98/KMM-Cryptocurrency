package com.yazantarifi.android.home.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.yazantarifi.android.home.action.CategoriesAction
import com.yazantarifi.android.home.state.CategoriesState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.CategoriesDataSource
import com.yazantarifi.coina.models.Category
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.listeners.CoinaLoadingStateListener
import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesDataSource: CategoriesDataSource,
    private val applicationApiManager: ApplicationApiManager
): CoinaViewModel<CategoriesAction, CategoriesState>(), CoinaStateListener<CategoriesState>, CoinaLoadingStateListener {

    val screenLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    val screenContentState: MutableState<ArrayList<Category>> by lazy { mutableStateOf(arrayListOf()) }
    init {
        registerStateListener(this)
        registerLoadingStateListener(this)
    }

    override fun onNewAction(action: CategoriesAction) {
        if (action is CategoriesAction.GetCategories) {
            getCategoriesList()
        }
    }

    private fun getCategoriesList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (state.value is CategoriesState.LoadingState) {
                onLoadingStateChanged(true)
                applicationApiManager.getCategories(categoriesDataSource) {
                    onLoadingStateChanged(false)
                    it.handleResult({
                        it?.let {
                            onAcceptNewState(CategoriesState.ListState(it))
                        }
                    }, {

                    })
                }
            }
        }
    }

    override fun getInitialState(): CategoriesState {
        if (categoriesDataSource.isDataSourceEmpty()) return CategoriesState.LoadingState
        return CategoriesState.ListState(categoriesDataSource.getCategories())
    }

    override fun onLoadingStateChanged(newState: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            screenLoadingState.value = newState
        }
    }

    override fun onUpdateState(newState: CategoriesState) {
        if (newState is CategoriesState.ListState) {
            screenContentState.value = newState.data
        }
    }
}