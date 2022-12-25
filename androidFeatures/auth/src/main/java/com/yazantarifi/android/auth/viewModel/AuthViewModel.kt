package com.yazantarifi.android.auth.viewModel

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.yazantarifi.android.auth.useCases.AuthUseCase
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
    private val getAuthUseCase: AuthUseCase
): CoinaViewModel<AuthAction, AuthState>() {

    val userEmailState: MutableState<String> by lazy { mutableStateOf("") }
    val userPasswordState: MutableState<String> by lazy { mutableStateOf("") }
    val loginLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    val loginStateListener: MutableState<Boolean> by lazy { mutableStateOf(false) }
    val loginErrorMessageListener: MutableState<String> by lazy { mutableStateOf("") }

    init {
        initViewModel()
    }

    private fun onGetLoginInformation() {
        getAuthUseCase(AuthUseCase.Args(userEmailState.value, userPasswordState.value))
    }

    override fun executeAction(action: AuthAction) {
        when (action) {
            is AuthAction.LoginAction -> onGetLoginInformation()
        }
    }

    override fun getInitialState(): AuthState {
        return AuthState.EmptyState
    }

    override fun onExceptionListenerTriggered(key: String, value: Throwable) {
        loginErrorMessageListener.value = value.message ?: ""
    }

    override fun onListenerTriggered(key: String, value: CoinaApplicationState<Any>) {
        if (key == AuthUseCase.KEY) {
            value.handleResult({
                it?.let {
                    if (it is Boolean) {
                        provider.updateLoggedInUser(true)
                        loginStateListener.value = true
                    }
                }
            }, {
                loginErrorMessageListener.value = it.exception?.message ?: ""
                loginErrorMessageListener.value = ""
            }, {
                loginLoadingState.value = it
            })
        }
    }

    override fun getSupportedUseCases(): ArrayList<CoinaUseCaseType<Any>> {
        return ArrayList<CoinaUseCaseType<Any>>().apply {
            add(getAuthUseCase as CoinaUseCaseType<Any>)
        }
    }

}
