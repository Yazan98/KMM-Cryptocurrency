package com.yazantarifi.coina.database

import com.yazantarifi.coina.database.models.RealmCoinImage
import com.yazantarifi.coina.models.CoinImage
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.types.BaseRealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

class CoinImagesDatabase : CoinaBaseDataSource() {

    suspend fun writeImages(data: ArrayList<CoinImage>) {
        withContext(Dispatchers.Default) {
            val realmInstance = getRealmInstance()
            realmInstance.writeBlocking {
                data.forEach {
                    copyToRealm(it.toRealmInstance(), UpdatePolicy.ALL)
                }
            }
            closeRealmInstance(realmInstance)
        }
    }

    suspend fun getImages(): ArrayList<CoinImage> {
        return withContext(Dispatchers.Default) {
            val images = ArrayList<CoinImage>()
            val realmInstance = getRealmInstance()
            realmInstance.query(RealmCoinImage::class).find().forEach {
                images.add(it.toCoinImage())
            }
            closeRealmInstance(realmInstance)
            images
        }
    }

    override fun getSchema(): Set<KClass<out BaseRealmObject>> {
        return setOf(RealmCoinImage::class)
    }

}
