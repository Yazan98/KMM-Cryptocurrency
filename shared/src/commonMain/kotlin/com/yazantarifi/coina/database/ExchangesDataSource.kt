package com.yazantarifi.coina.database

import com.yazantarifi.coina.database.models.RealmExchangeModel
import com.yazantarifi.coina.models.ExchangeModel
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.types.BaseRealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

class ExchangesDataSource : CoinaBaseDataSource() {

    suspend fun writeExchangesData(exchanges: ArrayList<ExchangeModel>) {
        withContext(Dispatchers.Default) {
            val realmInstance = getRealmInstance()
            realmInstance.writeBlocking {
                exchanges.forEach { exchange ->
                    try {
                        copyToRealm(ExchangeModel.toRealmExchangeModel(exchange), UpdatePolicy.ALL)
                    } catch (ex: Exception) {
                        println("Exception While Writing Exchange")
                        ex.printStackTrace()
                    }
                }
            }
            closeRealmInstance(realmInstance)
        }
    }

    fun isDataSourceEmpty(): Boolean {
        return isDataSourceEmpty(RealmExchangeModel::class)
    }

    fun getExchanges(): ArrayList<ExchangeModel> {
        val realmInstance = getRealmInstance()
        val exchanges = ArrayList<ExchangeModel>()
        realmInstance.query(RealmExchangeModel::class).find().forEach {
            exchanges.add(RealmExchangeModel.toExchangeModel(it))
        }
        closeRealmInstance(realmInstance)
        return exchanges
    }

    override fun getSchema(): Set<KClass<out BaseRealmObject>> {
        return setOf(RealmExchangeModel::class)
    }

    override fun getDataSourceName(): String {
        return "exchanges.realm"
    }

}
