package com.yazantarifi.coina.models

import com.yazantarifi.coina.database.models.RealmCoinImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinImage(
    @SerialName("asset_id") val id: String? = "",
    @SerialName("url") val url: String? = ""
) {
    fun toRealmInstance(): RealmCoinImage {
        return RealmCoinImage().apply {
            imageId = id ?: ""
            imageUrl = url ?: ""
        }
    }
}