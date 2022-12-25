package com.yazantarifi.android.home.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.yazantarifi.android.home.action.CategoriesCoinAction
import com.yazantarifi.android.home.state.CategoriesCoinState
import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.useCases.GetCategoryCoinsUseCase
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCaseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesCoinViewModel @Inject constructor(
    private val getCategoryCoinsUseCase: GetCategoryCoinsUseCase
): CoinaViewModel<CategoriesCoinAction, CategoriesCoinState>() {

    val screenLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    val screenContentState: MutableState<ArrayList<CoinModel>> by lazy { mutableStateOf(arrayListOf()) }
    init {
        initViewModel()
    }

    override fun executeAction(action: CategoriesCoinAction) {
        if (action is CategoriesCoinAction.GetCategoryCoin) {
            getCoinsByCategoryName(action.categoryName)
        }
    }

    private fun getCoinsByCategoryName(categoryName: String) {
        scope.launch(Dispatchers.IO) {
            getCategoryCoinsUseCase(GetCategoryCoinsUseCase.Args(categoryName))
        }
    }

    override fun getInitialState(): CategoriesCoinState {
        return CategoriesCoinState.LoadingState
    }

    override fun onListenerTriggered(key: String, value: CoinaApplicationState<Any>) {
        if (key == GetCategoryCoinsUseCase.KEY) {
            value.handleResult({
                (it as? ArrayList<CoinModel>)?.let {
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
            add(getCategoryCoinsUseCase as CoinaUseCaseType<Any>)
        }
    }

}