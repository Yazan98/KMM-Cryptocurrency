package com.yazantarifi.coina

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform