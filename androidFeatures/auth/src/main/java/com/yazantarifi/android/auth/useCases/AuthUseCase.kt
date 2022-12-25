package com.yazantarifi.android.auth.useCases

import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val apiManager: ApplicationApiManager,
    private val database: CoinsDataSource
): CoinaUseCase<AuthUseCase.Args, Boolean>() {

    override fun run(args: Args) {
        launch(Dispatchers.IO) {

        }
    }

    data class Args(
        val email: String,
        val password: String
    )

}