package com.yazantarifi.coina.useCases

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.ExchangesDataSource
import com.yazantarifi.coina.models.ExchangeModel
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetExchangesUseCase constructor(
    private val apiManager: ApplicationApiManager,
    private val database: ExchangesDataSource
): CoinaUseCase<Unit, ArrayList<ExchangeModel>>() {
    companion object {
        const val KEY = "GetExchangesUseCase"
    }

    override fun run(args: Unit) {
        launch(Dispatchers.Default) {
            val isDataSourceEmpty = database.isDataSourceEmpty()
            if (!isDataSourceEmpty) {
                onSendState(CoinaApplicationState.Success(database.getExchanges()))
            }

            if (isDataSourceEmpty) {
                onSendLoadingState(true)
            }

            apiManager.getExchanges(database) {
                it.handleResult({
                    onSendLoadingState(false)
                    onSendState(CoinaApplicationState.Success(it))
                    launch {
                        it?.let { it1 -> database.writeExchangesData(it1) }
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
