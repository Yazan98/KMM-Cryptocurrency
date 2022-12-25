package com.yazantarifi.coina.useCases

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.CategoriesDataSource
import com.yazantarifi.coina.models.Category
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetCategoriesUseCase constructor(
    private val apiManager: ApplicationApiManager,
    private val dataSource: CategoriesDataSource
): CoinaUseCase<Unit, ArrayList<Category>>() {

    companion object {
        const val KEY = "CategoriesUseCase"
    }

    override fun run(args: Unit) {
        launch(Dispatchers.Default) {
            if (!dataSource.isDataSourceEmpty()) {
                onSendState(CoinaApplicationState.Success(dataSource.getCategories()))
                return@launch
            }

            onSendLoadingState(true)
            apiManager.getCategories(dataSource) {
                it.handleResult({
                    onSendLoadingState(false)
                    onSendState(CoinaApplicationState.Success(it))
                    launch {
                        it?.let {
                            dataSource.writeCategoriesData(it)
                        }
                    }
                }, {
                    onSendLoadingState(false)
                    onSendState(CoinaApplicationState.Error(it.exception))
                })
            }
        }
    }

    override fun getUseCaseKey(): String {
        return KEY
    }
}