package com.yazantarifi.coina.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.UnknownHostException

actual class HttpBaseClient {

    actual val httpClient: HttpClient = HttpClient {
        defaultRequest {
            host = ""
            contentType(ContentType.Application.Json)
        }

        expectSuccess = false
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
                isLenient = true
            })
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
                var error = null
                when (cause) {
                    is ResponseException -> {}
                    is UnknownHostException -> {}
                    else -> {}
                }

                throw Exception("")
            }
        }
    }

}