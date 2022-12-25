package com.yazantarifi.coina.useCases

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.errors.CoinaValidationException
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthUseCase constructor(
    private val apiManager: ApplicationApiManager,
    private val database: CoinsDataSource
): CoinaUseCase<AuthUseCase.Args, Boolean>() {

    companion object {
        const val KEY = "AuthUseCase"
    }

    override fun run(args: Args) {
        launch(Dispatchers.Default) {
            if (args.email.isEmpty()) {
                onSendErrorState(CoinaValidationException("Email Can't Be Empty !!"))
                return@launch
            }

            if (args.password.isEmpty()) {
                onSendErrorState(CoinaValidationException("Password Can't Be Empty !!"))
                return@launch
            }

            if (!database.isDataSourceEmpty()) {
                onSendState(CoinaApplicationState.Success(true))
                return@launch
            }

            onSendLoadingState(true)
            apiManager.getCoins(database) {
                it.handleResult({
                    onSendLoadingState(false)
                    onSendState(CoinaApplicationState.Success(true))
                }, {
                    onSendLoadingState(false)
                    onSendState(CoinaApplicationState.Error(it.exception))
                }, {
                    onSendLoadingState(false)
                })
            }
        }
    }

    data class Args(
        val email: String,
        val password: String
    )

    override fun getUseCaseKey(): String {
        return KEY
    }

}