package com.yazantarifi.coina.useCases

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetCoinsUseCase constructor(
    private val apiManager: ApplicationApiManager,
    private val database: CoinsDataSource
): CoinaUseCase<Unit, ArrayList<CoinModel>>() {

    companion object {
        const val KEY = "GetCoinsUseCase"
    }

    override fun run(args: Unit) {
        launch(Dispatchers.Default) {
            val isDataSourceEmpty = database.isDataSourceEmpty()
            if (!isDataSourceEmpty) {
                onSendState(CoinaApplicationState.Success(database.getCoins()))
            }

            if (isDataSourceEmpty) {
                onSendLoadingState(true)
            }

            apiManager.getCoins(database) {
                it.handleResult({
                    onSendLoadingState(false)
                    onSendState(CoinaApplicationState.Success(it))
                    launch {
                        it?.let { it1 -> database.writeCoinsData(it1) }
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