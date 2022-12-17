package com.yazantarifi.coina.api

import com.yazantarifi.coina.api.errors.CoinaNoInternetException
import com.yazantarifi.coina.api.errors.CoinaUnknownException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.UnknownHostException

actual class HttpBaseClient {

    actual val httpClient: HttpClient = HttpClient {
        defaultRequest {
            host = CoinaApiInfo.BASE_URL
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Type", "application/json")
                append("Accept", "application/json")
                append(CoinaApiInfo.AUTH_HEADER_NAME, CoinaApiInfo.API_KEY)
            }
        }

        expectSuccess = false
        developmentMode = true
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        HttpResponseValidator {
            validateResponse { response ->
                when (response.status.value) {
                    CoinaResponseCode.ERROR_RESPONSE_CODE_NOT_FOUND -> {}
                    CoinaResponseCode.ERROR_RESPONSE_CODE_REDIRECT -> {}
                    CoinaResponseCode.ERROR_RESPONSE_CODE_UNAUTHORIZED -> {}
                }
            }

            handleResponseExceptionWithRequest { cause: Throwable, request: HttpRequest ->
                when (cause) {
                    is ResponseException -> {
                        throw cause
                    }

                    is UnknownHostException -> {
                        throw CoinaNoInternetException()
                    }

                    else -> {
                        throw CoinaUnknownException(cause)
                    }
                }
            }
        }

    }

}