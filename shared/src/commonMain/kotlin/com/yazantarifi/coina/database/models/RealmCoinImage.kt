package com.yazantarifi.coina.database.models

import com.yazantarifi.coina.models.CoinImage
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class RealmCoinImage() : RealmObject {
    @PrimaryKey
    var imageId: String = ""
    var imageUrl: String = ""

    fun toCoinImage(): CoinImage {
        return CoinImage(imageId, imageUrl)
    }
}
