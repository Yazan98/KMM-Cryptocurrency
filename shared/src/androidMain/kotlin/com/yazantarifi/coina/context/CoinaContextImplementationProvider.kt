package com.yazantarifi.coina.context

import android.content.Context


actual fun CoinaContext.putInt(key: String, value: Int) {
    getSpEditor().putInt(key, value).apply()
}

actual fun CoinaContext.getInt(key: String, default: Int): Int {
    return  getSp().getInt(key, default )
}

actual fun CoinaContext.putString(key: String, value: String) {
    getSpEditor().putString(key, value).apply()
}

actual fun CoinaContext.getString(key: String): String? {
    return  getSp().getString(key, null)
}

actual fun CoinaContext.putBool(key: String, value: Boolean) {
    getSpEditor().putBoolean(key, value).apply()
}

actual fun CoinaContext.getBool(key: String, default: Boolean): Boolean {
    return getSp().getBoolean(key, default)
}

private fun CoinaContext.getSp() = getSharedPreferences(CoinaStorageKeys.STORAGE_MAIN_KEY, Context.MODE_PRIVATE)

private fun CoinaContext.getSpEditor() = getSp().edit()