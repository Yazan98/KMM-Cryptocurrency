package com.yazantarifi.coina.database.models

import com.yazantarifi.coina.models.Category
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class RealmCategory: RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String? = ""
    var images: RealmList<String> = realmListOf()
    var content: String? = ""
    var marketGap: Long? = 0L

    companion object {
        fun toCategory(realmCategory: RealmCategory): Category {
            val images = ArrayList<String>()
            realmCategory.images?.forEach {
                images.add(it)
            }

            return Category(
                realmCategory.id,
                realmCategory.name,
                realmCategory.content,
                realmCategory.marketGap,
                images
            )
        }
    }

}
