package com.yazantarifi.coina.database

import com.yazantarifi.coina.database.models.RealmExchangeItem
import com.yazantarifi.coina.models.CoinExchange
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.types.BaseRealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

class CoinExchangesDataSource : CoinaBaseDataSource() {

    suspend fun writeExchanges(exchanges: ArrayList<CoinExchange>) {
        withContext(Dispatchers.Default) {
            val realmInstance = getRealmInstance()
            realmInstance.writeBlocking {
                exchanges.forEach {
                    copyToRealm(it.toRealmExchange(), UpdatePolicy.ALL)
                }
            }
            closeRealmInstance(realmInstance)
        }
    }

    fun getImages(): ArrayList<CoinExchange> {
        val images = ArrayList<CoinExchange>()
        val realmInstance = getRealmInstance()
        realmInstance.query(RealmExchangeItem::class).find().forEach {
            images.add(it.toCoinExchange())
        }
        closeRealmInstance(realmInstance)
        return images
    }

    fun isExchangesEmpty(): Boolean {
        return try {
            val realmInstance = getRealmInstance()
            var isExchangesEmpty = false
            isExchangesEmpty = realmInstance.query(RealmExchangeItem::class).count().find() == 0L
            closeRealmInstance(realmInstance)
            isExchangesEmpty
        } catch (ex: Exception) {
            println("Exchanges Error : ${ex.message}")
            true
        }
    }

    override fun getDataSourceName(): String {
        return "exchanges.realm"
    }

    override fun getSchema(): Set<KClass<out BaseRealmObject>> {
        return setOf(RealmExchangeItem::class)
    }

}