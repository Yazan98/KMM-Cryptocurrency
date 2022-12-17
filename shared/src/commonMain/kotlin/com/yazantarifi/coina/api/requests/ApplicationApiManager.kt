package com.yazantarifi.coina.api.requests

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.CoinaApiInfo
import com.yazantarifi.coina.api.CoinaResponseCode
import com.yazantarifi.coina.models.CoinImage
import com.yazantarifi.coina.ofInnerClassParameter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.suitableCharset
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.printStack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ApplicationApiManager constructor(private val httpClient: HttpClient): ApplicationApiManagerImplementation {

    init {
        val converter = getSerializable()
        httpClient.responsePipeline.intercept(HttpResponsePipeline.Transform) { (info, body) ->
            if (body !is ByteReadChannel) return@intercept

            val response = context.response
            val apiResponse = if (response.status.value >= CoinaResponseCode.RESPONSE_CODE_SUCCESS && response.status.value < CoinaResponseCode.ERROR_RESPONSE_CODE_REDIRECT) {
                CoinaApplicationState.Success(converter.deserialize(context.request.headers.suitableCharset(), info.ofInnerClassParameter(), body))
            } else {
                CoinaApplicationState.Error(responseCode = response.status.value)
            }

            proceedWith(HttpResponseContainer(info, apiResponse))
        }
    }

    override suspend fun getCoinsImages(onNewStateTriggered: (CoinaApplicationState<ArrayList<CoinImage>>) -> Unit) {
        withContext(Dispatchers.Default) {
            try {
                val request: ArrayList<CoinImage> = httpClient.get(CoinaApiInfo.BASE_URL + "assets/icons/200").body()
                onNewStateTriggered(CoinaApplicationState.Success(request))
            } catch (ex: Exception) {
                onNewStateTriggered(CoinaApplicationState.Error(ex))
                ex.printStackTrace()
            }
        }
    }

    private fun getSerializable(): KotlinxSerializationConverter {
        return KotlinxSerializationConverter(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            allowSpecialFloatingPointValues = true
            isLenient = true
        })
    }
}
