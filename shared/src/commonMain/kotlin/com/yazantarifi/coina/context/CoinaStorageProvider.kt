package com.yazantarifi.coina.context

class CoinaStorageProvider constructor(private val provider: CoinaStorageKeyValue): CoinaStorageProviderImplementation {
    override fun updateLoggedInUser(newState: Boolean) {
        provider.put(CoinaStorageKeys.IS_LOGGED_IN, newState)
    }

    override fun isUserLoggedIn(): Boolean {
        return provider.getBool(CoinaStorageKeys.IS_LOGGED_IN, false)
    }
}

interface CoinaStorageProviderImplementation {
    fun updateLoggedInUser(newState: Boolean)
    fun isUserLoggedIn(): Boolean
}