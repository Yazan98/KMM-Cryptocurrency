package com.yazantarifi.android.auth.viewModel

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.context.CoinaStorageProvider
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.listeners.CoinaLoadingStateListener
import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import com.yazantarifi.coina.viewModels.useCases.CoinaUseCaseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val provider: CoinaStorageProvider,
    private val apiManager: ApplicationApiManager,
    private val database: CoinsDataSource
): CoinaViewModel<AuthAction, AuthState>() {

    val userEmailState: MutableState<String> by lazy { mutableStateOf("") }
    val userPasswordState: MutableState<String> by lazy { mutableStateOf("") }
    val loginLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    val loginStateListener: MutableState<Boolean> by lazy { mutableStateOf(false) }

    init {
        initViewModel()
    }

    fun isEmailValid(): Boolean {
        return !TextUtils.isEmpty(userEmailState.value)
    }

    fun isPasswordValid(): Boolean {
        return !TextUtils.isEmpty(userPasswordState.value)
    }

    private fun onGetLoginInformation() {
        viewModelScope.launch(Dispatchers.IO) {
            onAcceptNewState(AuthState.LoadingState(true))
            apiManager.getCoins(database) {
                it.handleResult({
                    provider.updateLoggedInUser(true)
                    onAcceptNewState(AuthState.SuccessState)
                }, {

                }, {
                    onAcceptNewState(AuthState.LoadingState(it))
                })
            }
        }
    }

    override fun executeAction(action: AuthAction) {
        when (action) {
            is AuthAction.LoginAction -> onGetLoginInformation()
        }
    }

    override fun getInitialState(): AuthState {
        return AuthState.EmptyState
    }

    override fun getListeners(): HashMap<String, ReceiveChannel<CoinaApplicationState<Any>>> {
        TODO("Not yet implemented")
    }

    override fun onListenerTriggered(key: String, value: CoinaApplicationState<Any>) {
        TODO("Not yet implemented")
    }

    override fun getSupportedUseCases(): ArrayList<CoinaUseCaseType<Any>> {
        TODO("Not yet implemented")
    }

}
