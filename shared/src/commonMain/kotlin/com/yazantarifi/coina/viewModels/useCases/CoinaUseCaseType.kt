package com.yazantarifi.coina.viewModels.useCases

import com.yazantarifi.coina.CoinaApplicationState
import kotlinx.coroutines.channels.ReceiveChannel

interface CoinaUseCaseType<Result> {

    fun clear()

    fun getChannelListener(): ReceiveChannel<CoinaApplicationState<Result>>

    fun getErrorChannelListener(): ReceiveChannel<Throwable>

    fun getUseCaseKey(): String

}