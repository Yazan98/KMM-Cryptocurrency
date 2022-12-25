package com.yazantarifi.coina.useCases

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetCoinsUseCase: CoinaUseCase<Unit, ArrayList<CoinModel>>() {

    private var apiManager: ApplicationApiManager? = null
    private var database: CoinsDataSource? = null

    fun addDependencies(apiManager: ApplicationApiManager, database: CoinsDataSource): GetCoinsUseCase {
        this.apiManager = apiManager
        this.database = database
        return this
    }

    companion object {
        const val KEY = "GetCoinsUseCase"
    }

    override fun run(args: Unit) {
        launch(Dispatchers.Default) {
            val isDataSourceEmpty = database?.isDataSourceEmpty() ?: false
            if (!isDataSourceEmpty) {
                onSendState(CoinaApplicationState.Success(database?.getCoins()))
            }

            if (isDataSourceEmpty) {
                onSendLoadingState(true)
            }

            apiManager?.getCoins(database ?: return@launch) {
                it.handleResult({
                    onSendLoadingState(false)
                    onSendState(CoinaApplicationState.Success(it))
                    launch {
                        it?.let { it1 -> database?.writeCoinsData(it1) }
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