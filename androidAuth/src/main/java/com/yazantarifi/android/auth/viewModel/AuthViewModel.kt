package com.yazantarifi.android.auth.viewModel

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.context.CoinaStorageProvider
import com.yazantarifi.coina.database.CoinImagesDatabase
import com.yazantarifi.coina.viewModels.CoinaViewModel
import com.yazantarifi.coina.viewModels.listeners.CoinaLoadingStateListener
import com.yazantarifi.coina.viewModels.listeners.CoinaStateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val provider: CoinaStorageProvider,
    private val apiManager: ApplicationApiManager,
    private val database: CoinImagesDatabase
): CoinaViewModel<AuthAction, AuthState>(), CoinaStateListener<AuthState>, CoinaLoadingStateListener {

    val userEmailState: MutableState<String> by lazy { mutableStateOf("") }
    val userPasswordState: MutableState<String> by lazy { mutableStateOf("") }
    val loginLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }
    val loginStateListener: MutableState<Boolean> by lazy { mutableStateOf(false) }

    init {
        registerStateListener(this)
        registerLoadingStateListener(this)
    }

    fun isEmailValid(): Boolean {
        return !TextUtils.isEmpty(userEmailState.value)
    }

    fun isPasswordValid(): Boolean {
        return !TextUtils.isEmpty(userPasswordState.value)
    }

    fun onGetLoginInformation() {
        viewModelScope.launch(Dispatchers.IO) {
            onAcceptLoadingState(true)
            apiManager.getCoinsImages(database) {
                it.handleResult({
                    onAcceptLoadingState(false)
                    provider.updateLoggedInUser(true)
                    onAcceptNewState(AuthState.SuccessState)
                }, {
                    onAcceptLoadingState(false)
                })
            }
        }
    }

    override fun onLoadingStateChanged(newState: Boolean) {
        this.loginLoadingState.value = newState
    }

    override fun onUpdateState(newState: AuthState) {
        if (newState is AuthState.SuccessState) {
            loginStateListener.value = true
        }
    }

    override fun onNewAction(action: AuthAction) {
        when (action) {
            is AuthAction.LoginAction -> onGetLoginInformation()
        }
    }

    override fun getInitialState(): AuthState {
        return AuthState.EmptyState
    }

}
