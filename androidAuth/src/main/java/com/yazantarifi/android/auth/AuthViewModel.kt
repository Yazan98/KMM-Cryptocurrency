package com.yazantarifi.android.auth

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazantarifi.coina.context.CoinaStorageProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val provider: CoinaStorageProvider
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
            delay(3000)
            // Execute Your UseCase Here (Database or Api Request)
            loginLoadingState.value = false
            provider.updateLoggedInUser(true)
            onUserLoggedInResponse()
        }
    }
}
