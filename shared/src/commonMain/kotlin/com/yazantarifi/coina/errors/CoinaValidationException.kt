package com.yazantarifi.coina.errors

class CoinaValidationException constructor(
    override val message: String? = ""
) : Throwable(message)
