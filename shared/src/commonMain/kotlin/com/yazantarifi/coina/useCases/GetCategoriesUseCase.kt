package com.yazantarifi.coina.useCases

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.CategoriesDataSource
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.models.Category
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetCategoriesUseCase: CoinaUseCase<Unit, ArrayList<Category>>() {

    private var apiManager: ApplicationApiManager? = null
    private var dataSource: CategoriesDataSource? = null

    fun addDependencies(apiManager: ApplicationApiManager, database: CategoriesDataSource): GetCategoriesUseCase {
        this.apiManager = apiManager
        this.dataSource = database
        return this
    }

    companion object {
        const val KEY = "CategoriesUseCase"
    }

    override fun run(args: Unit) {
        launch(Dispatchers.Default) {
            if (dataSource?.isDataSourceEmpty() != true) {
                onSendState(CoinaApplicationState.Success(dataSource?.getCategories()))
                return@launch
            }

            onSendLoadingState(true)
            apiManager?.getCategories(dataSource ?: return@launch) {
                it.handleResult({
                    onSendLoadingState(false)
                    onSendState(CoinaApplicationState.Success(it))
                    launch {
                        it?.let {
                            dataSource?.writeCategoriesData(it)
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