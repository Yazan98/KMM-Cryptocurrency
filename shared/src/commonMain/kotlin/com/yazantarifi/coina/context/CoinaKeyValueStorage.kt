package com.yazantarifi.coina.context


expect fun CoinaContext.putInt(key: String, value: Int)

expect fun CoinaContext.getInt(key: String, default: Int): Int

expect fun CoinaContext.putString(key: String, value: String)

expect fun CoinaContext.getString(key: String) : String?

expect fun CoinaContext.putBool(key: String, value: Boolean)

expect fun CoinaContext.getBool(key: String, default: Boolean): Boolean
