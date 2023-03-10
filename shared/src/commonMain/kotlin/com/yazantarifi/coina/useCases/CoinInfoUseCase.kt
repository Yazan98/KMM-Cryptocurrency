package com.yazantarifi.coina.useCases

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.errors.CoinaValidationException
import com.yazantarifi.coina.models.CoinInformation
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinInfoUseCase: CoinaUseCase<CoinInfoUseCase.Args, CoinInformation>() {

    private var apiManager: ApplicationApiManager? = null

    fun addDependencies(apiManager: ApplicationApiManager): CoinInfoUseCase {
        this.apiManager = apiManager
        return this
    }

    data class Args(val key: String)
    companion object {
        const val KEY = "CoinUseCase"
    }

    override fun run(args: Args) {
        launch(Dispatchers.Default) {
            if (args.key.isEmpty()) {
                onSendState(CoinaApplicationState.Error(CoinaValidationException("Coin Id Can't be Empty")))
                return@launch
            }

            onSendLoadingState(true)
            apiManager?.getCoinInformation(args.key) {
                it.handleResult({
                    onSendLoadingState(false)
                    onSendState(CoinaApplicationState.Success(it))
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