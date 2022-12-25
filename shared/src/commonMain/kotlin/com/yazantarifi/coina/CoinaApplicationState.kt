package com.yazantarifi.coina

import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.platformType
import kotlin.reflect.KClass

sealed class CoinaApplicationState<out T> {
    data class Success<out T : Any>(
        val data: T?
    ) : CoinaApplicationState<T>()

    object Loading: CoinaApplicationState<Nothing>()
    object Loaded: CoinaApplicationState<Nothing>()

    data class Error(
        val exception: Throwable? = null,
        val responseCode: Int = -1
    ) : CoinaApplicationState<Nothing>()

    fun handleResult(onSuccess: ((responseData: T?) -> Unit)?, onError: ((error: Error) -> Unit)?, onLoading: ((loadingState: Boolean) -> Unit)? = null) {
        when (this) {
            is Success -> {
                onSuccess?.invoke(this.data)
            }
            is Error -> {
                onError?.invoke(this)
            }
            is Loading -> {
                onLoading?.invoke(true)
            }
            is Loaded -> {
                onLoading?.invoke(false)
            }
        }
    }
}

fun TypeInfo.ofInnerClassParameter(): TypeInfo {
    val typeProjection = kotlinType?.arguments?.get(0)
    val kType = typeProjection!!.type!!
    return TypeInfo(kType.classifier as KClass<*>, kType.platformType)
}