package com.yazantarifi.coina.database.models

import com.yazantarifi.coina.models.ExchangeModel
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class RealmExchangeModel: RealmObject {
    @PrimaryKey
    var id: String = ""

    var name: String? = ""
    var country: String? = ""
    var description: String? = ""
    var url: String? = ""
    var image: String? = ""
    var yearEstablished: Int? = 0

    companion object {
        fun toExchangeModel(model: RealmExchangeModel): ExchangeModel {
            return ExchangeModel(
                model.id,
                model.name,
                model.country,
                model.description,
                model.url,
                model.image,
                model.yearEstablished
            )
        }
    }
}