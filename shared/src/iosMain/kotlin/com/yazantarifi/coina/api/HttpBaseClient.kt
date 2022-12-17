package com.yazantarifi.coina.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual class HttpBaseClient {
    actual val httpClient: HttpClient = HttpClient(Darwin) {
        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }
}