package com.yazantarifi.coina.useCases

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetCategoryCoinsUseCase constructor(
    private val apiManager: ApplicationApiManager
): CoinaUseCase<GetCategoryCoinsUseCase.Args, ArrayList<CoinModel>>() {

    data class Args(val categoryName: String)
    companion object {
        const val KEY = "GetCategoryCoinsUseCase"
    }

    override fun run(args: Args) {
        launch(Dispatchers.Default) {
            onSendLoadingState(true)
            apiManager.getCoinsByCategoryName(args.categoryName) {
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