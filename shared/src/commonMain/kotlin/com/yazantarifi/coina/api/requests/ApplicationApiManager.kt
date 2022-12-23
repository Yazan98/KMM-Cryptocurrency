package com.yazantarifi.coina.api.requests

import com.yazantarifi.coina.CoinaApplicationState
import com.yazantarifi.coina.api.CoinaApiInfo
import com.yazantarifi.coina.api.CoinaResponseCode
import com.yazantarifi.coina.database.CategoriesDataSource
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.database.ExchangesDataSource
import com.yazantarifi.coina.models.Category
import com.yazantarifi.coina.models.CoinInformation
import com.yazantarifi.coina.models.CoinModel
import com.yazantarifi.coina.models.ExchangeModel
import com.yazantarifi.coina.ofInnerClassParameter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.suitableCharset
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.Dispatchers
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

    override suspend fun getCoins(
        database: CoinsDataSource,
        onNewStateTriggered: (CoinaApplicationState<ArrayList<CoinModel>>) -> Unit
    ) {
        withContext(Dispatchers.Default) {
            try {
                val request: ArrayList<CoinModel> = httpClient.get(CoinaApiInfo.COINS_BASE_URL + CoinaApiLinks.COINS_MARKETPLACE).body()
                database.writeCoinsData(request)
                onNewStateTriggered(CoinaApplicationState.Success(request))
            } catch (ex: Exception) {
                onNewStateTriggered(CoinaApplicationState.Error(ex))
                ex.printStackTrace()
            }
        }
    }

    override suspend fun getCoinsByCategoryName(
        categoryName: String,
        onNewStateTriggered: (CoinaApplicationState<ArrayList<CoinModel>>) -> Unit
    ) {
        withContext(Dispatchers.Default) {
            try {
                val request: ArrayList<CoinModel> = httpClient.get(CoinaApiInfo.COINS_BASE_URL + CoinaApiLinks.COINS_MARKETPLACE + "&category=$categoryName").body()
                onNewStateTriggered(CoinaApplicationState.Success(request))
            } catch (ex: Exception) {
                onNewStateTriggered(CoinaApplicationState.Error(ex))
                ex.printStackTrace()
            }
        }
    }

    override suspend fun getCoinInformation(
        key: String,
        onNewStateTriggered: (CoinaApplicationState<CoinInformation>) -> Unit
    ) {
        withContext(Dispatchers.Default) {
            try {
                val request: CoinInformation = httpClient.get(CoinaApiInfo.COINS_BASE_URL + CoinaApiLinks.COIN_INFO.replace("{key}", key)).body()
                onNewStateTriggered(CoinaApplicationState.Success(request))
            } catch (ex: Exception) {
                onNewStateTriggered(CoinaApplicationState.Error(ex))
                ex.printStackTrace()
            }
        }
    }

    override suspend fun getExchanges(
        database: ExchangesDataSource,
        onNewStateTriggered: (CoinaApplicationState<ArrayList<ExchangeModel>>) -> Unit
    ) {
        withContext(Dispatchers.Default) {
            try {
                val request: ArrayList<ExchangeModel> = httpClient.get(CoinaApiInfo.COINS_BASE_URL + CoinaApiLinks.COINS_LIST_EXCHANGES).body()
                database.writeExchangesData(request)
                onNewStateTriggered(CoinaApplicationState.Success(request))
            } catch (ex: Exception) {
                onNewStateTriggered(CoinaApplicationState.Error(ex))
                ex.printStackTrace()
            }
        }
    }

    override suspend fun getCategories(
        database: CategoriesDataSource,
        onNewStateTriggered: (CoinaApplicationState<ArrayList<Category>>) -> Unit
    ) {
        withContext(Dispatchers.Default) {
            try {
                if (database.isDataSourceEmpty()) {
                    val request: ArrayList<Category> = httpClient.get(CoinaApiInfo.COINS_BASE_URL + CoinaApiLinks.COINS_LIST_CATEGORIES).body()
                    database.writeCategoriesData(request)
                    onNewStateTriggered(CoinaApplicationState.Success(request))
                } else {
                    onNewStateTriggered(CoinaApplicationState.Success(database.getCategories()))
                }
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
