package com.yazantarifi.coina

fun Int.formatDecimalSeparator(): String {
    return toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
        .replace(".", "")
}

fun Long.formatDecimalSeparator(): String {
    return toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
        .replace(".", "")
}

fun Double.formatDecimalSeparator(): String {
    return toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
        .replace(".", "")
}

object PriceExt {
    fun formatDecimalSeparator(price: Double): String {
        return price.toString()
            .reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()
            .replace(".", "")
    }
}