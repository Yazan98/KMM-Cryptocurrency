package com.yazantarifi.coina.models

import com.yazantarifi.coina.database.models.RealmExchangeModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeModel(
    @SerialName("id") val id: String? = "",
    @SerialName("name") val name: String? = "",
    @SerialName("country") val country: String? = "",
    @SerialName("description") val description: String? = "",
    @SerialName("url") val url: String? = "",
    @SerialName("image") val image: String? = "",
    @SerialName("year_established") val yearEstablished: Int? = 0,
) {
    companion object {
        fun toRealmExchangeModel(model: ExchangeModel): RealmExchangeModel {
            return RealmExchangeModel().apply {
                id = model.id ?: ""
                name = model.name ?: ""
                country = model.country ?: ""
                description = model.description ?: ""
                url = model.url ?: ""
                image = model.image ?: ""
                yearEstablished = model.yearEstablished ?: 0
            }
        }
    }

    fun getCountryText(): String {
        if (country.isNullOrEmpty()) {
            return "Unknown"
        } else {
            return country ?: ""
        }
    }
}
