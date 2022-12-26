package com.yazantarifi.coina.useCases

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.CategoriesDataSource
import com.yazantarifi.coina.database.ExchangesDataSource
import com.yazantarifi.coina.models.ExchangeModel
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetExchangesUseCase: CoinaUseCase<Unit, ArrayList<ExchangeModel>>() {

    private var apiManager: ApplicationApiManager? = null
    private var database: ExchangesDataSource? = null

    fun addDependencies(apiManager: ApplicationApiManager, database: ExchangesDataSource): GetExchangesUseCase {
        this.apiManager = apiManager
        this.database = database
        return this
    }

    companion object {
        const val KEY = "GetExchangesUseCase"
    }

    override fun run(args: Unit) {
        launch(Dispatchers.Default) {
            val isDataSourceEmpty = database?.isDataSourceEmpty() ?: false
            if (!isDataSourceEmpty) {
                onSendState(CoinaApplicationState.Success(database?.getExchanges()))
            }

            if (isDataSourceEmpty) {
                onSendLoadingState(true)
            }

            apiManager?.getExchanges(database ?: return@launch) {
                it.handleResult({
                    onSendLoadingState(false)
                    onSendState(CoinaApplicationState.Success(it))
                    launch {
                        it?.let { it1 -> database?.writeExchangesData(it1) }
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
