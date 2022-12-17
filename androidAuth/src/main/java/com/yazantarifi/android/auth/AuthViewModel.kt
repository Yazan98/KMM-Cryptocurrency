package com.yazantarifi.android.auth

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.context.CoinaStorageProvider
import com.yazantarifi.coina.database.CoinImagesDatabase
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
): ViewModel() {

    val userEmailState: MutableState<String> by lazy { mutableStateOf("") }
    val userPasswordState: MutableState<String> by lazy { mutableStateOf("") }
    val loginLoadingState: MutableState<Boolean> by lazy { mutableStateOf(false) }

    fun isEmailValid(): Boolean {
        return !TextUtils.isEmpty(userEmailState.value)
    }

    fun isPasswordValid(): Boolean {
        return !TextUtils.isEmpty(userPasswordState.value)
    }

    fun onGetLoginInformation(onUserLoggedInResponse: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            loginLoadingState.value = true
            apiManager.getCoinsImages(database) {
                it.handleResult({
                    loginLoadingState.value = false
                    provider.updateLoggedInUser(true)
                    onUserLoggedInResponse()
                }, {
                    loginLoadingState.value = false
                })
            }
        }
    }

}
