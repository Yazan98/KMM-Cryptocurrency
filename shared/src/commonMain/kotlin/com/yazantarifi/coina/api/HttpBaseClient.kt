package com.yazantarifi.coina.api

import io.ktor.client.HttpClient

expect class HttpBaseClient() {
    val httpClient: HttpClient
}