package com.yazantarifi.coina

import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.platformType
import kotlin.reflect.KClass

sealed class CoinaApplicationState<out T : Any> {
    data class Success<out T : Any>(
        val data: T?
    ) : CoinaApplicationState<T>()

    data class Error(
        val exception: Throwable? = null,
        val responseCode: Int = -1
    ) : CoinaApplicationState<Nothing>()

    fun handleResult(onSuccess: ((responseData: T?) -> Unit)?, onError: ((error: Error) -> Unit)?) {
        when (this) {
            is Success -> {
                onSuccess?.invoke(this.data)
            }
            is Error -> {
                onError?.invoke(this)
            }
        }
    }
}

fun TypeInfo.ofInnerClassParameter(): TypeInfo {
    val typeProjection = kotlinType?.arguments?.get(0)
    val kType = typeProjection!!.type!!
    return TypeInfo(kType.classifier as KClass<*>, kType.platformType)
}