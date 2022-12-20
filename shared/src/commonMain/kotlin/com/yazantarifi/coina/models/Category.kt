package com.yazantarifi.coina.models

import com.yazantarifi.coina.database.models.RealmCategory
import io.realm.kotlin.ext.realmListOf
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("id") var id: String? = "",
    @SerialName("name") var name: String? = "",
    @SerialName("content") var content: String? = "",
    @SerialName("market_cap") var marketGap: Long? = 0L,
    @SerialName("top_3_coins") var topCoins: ArrayList<String>? = null,
) {
    companion object {
        fun toRealmCategory(category: Category): RealmCategory {
            return RealmCategory().apply {
                id = category.id ?: ""
                name = category.name ?: ""
                marketGap = category.marketGap
                content = category.content

                val imagesTpAdd = realmListOf<String>()
                category.topCoins?.forEach {
                    imagesTpAdd.add(it)
                }

                images = imagesTpAdd
            }
        }
    }
}