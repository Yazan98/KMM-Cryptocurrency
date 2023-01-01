package com.yazantarifi.coina.viewModels.useCases

import com.yazantarifi.coina.CoinaApplicationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class CoinaUseCase<Arguments, Result>: CoroutineScope, CoinaUseCaseType<Result> {

    private val parentJob = SupervisorJob()
    private val backgroundDispatcher = Dispatchers.Default
    private val resultChannel: Channel<CoinaApplicationState<Result>> by lazy { Channel() }
    private val receiveChannel: ReceiveChannel<CoinaApplicationState<Result>> by lazy { resultChannel }

    override val coroutineContext: CoroutineContext
        get() = parentJob + backgroundDispatcher

    override fun getChannelListener(): ReceiveChannel<CoinaApplicationState<Result>> {
        return this.receiveChannel
    }

    protected fun onSendState(state: CoinaApplicationState<Result>) {
        launch {
            resultChannel.send(state)
        }
    }

    protected fun onSendLoadingState(isLoading: Boolean) {
        when (isLoading) {
            true -> onSendState(CoinaApplicationState.Loading)
            false -> onSendState(CoinaApplicationState.Loaded)
        }
    }

    override fun clear() {
        this.resultChannel.close()
        parentJob.cancel()
    }

    protected abstract fun run(args: Arguments)

    /**
     * Calling the Class With it's Name to Run the UseCase
     * UseCase Method is Called in Background Thread
     */
    operator fun invoke(params: Arguments) {
        launch {
            withContext(backgroundDispatcher) {
                run(params)
            }
        }
    }

    /**
     * Start Async Jobs Inside Each UseCase to Not Blocking the Current Thread
     */
    protected fun <T> startAsync(block: suspend () -> T): Deferred<T> = async(parentJob) {
        block()
    }

}
