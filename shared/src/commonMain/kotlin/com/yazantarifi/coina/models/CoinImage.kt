package com.yazantarifi.coina.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinImage(
    @SerialName("asset_id") val id: String? = "",
    @SerialName("url") val url: String? = ""
)