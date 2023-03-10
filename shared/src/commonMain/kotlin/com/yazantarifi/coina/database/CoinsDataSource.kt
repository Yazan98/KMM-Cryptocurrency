package com.yazantarifi.coina.database

import com.yazantarifi.coina.database.models.RealmCoinModel
import com.yazantarifi.coina.models.CoinModel
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.query.Sort
import io.realm.kotlin.types.BaseRealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

class CoinsDataSource : CoinaBaseDataSource() {

    suspend fun writeCoinsData(data: ArrayList<CoinModel>) {
        withContext(Dispatchers.Default) {
            val realmInstance = getRealmInstance()
            realmInstance.writeBlocking {
                data.forEach {
                    copyToRealm(CoinModel.toRealmCoinModel(it), UpdatePolicy.ALL)
                }
            }
            closeRealmInstance(realmInstance)
        }
    }

    fun getCoins(): ArrayList<CoinModel> {
        val coins = ArrayList<CoinModel>()
        val realmInstance = getRealmInstance()

        realmInstance.query(RealmCoinModel::class).sort(RealmCoinModel.MARKET_GAP_RANK, Sort.ASCENDING).find().forEach {
            coins.add(RealmCoinModel.toCoinModel(it))
        }

        closeRealmInstance(realmInstance)
        return coins
    }

    fun getCoinsBySearchQuery(query: String): ArrayList<CoinModel> {
        if (query.isEmpty()) {
            return getCoins()
        }

        val coins = ArrayList<CoinModel>()
        val realmInstance = getRealmInstance()

        realmInstance.query(RealmCoinModel::class)
            .query("name CONTAINS[c] '${query}' OR symbol CONTAINS[c] '${query}'")
            .sort(RealmCoinModel.MARKET_GAP_RANK, Sort.ASCENDING)
            .find()
            .forEach {
            coins.add(RealmCoinModel.toCoinModel(it))
        }

        closeRealmInstance(realmInstance)
        return coins
    }

    fun isDataSourceEmpty(): Boolean {
        return isDataSourceEmpty(RealmCoinModel::class)
    }

    override fun getSchema(): Set<KClass<out BaseRealmObject>> {
        return setOf(RealmCoinModel::class)
    }

    override fun getDataSourceName(): String {
        return "coins.realm"
    }
}