package com.yazantarifi.coina.database

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.BaseRealmObject
import kotlin.reflect.KClass

abstract class CoinaBaseDataSource {

    private fun getRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder(schema = getSchema())
            .deleteRealmIfMigrationNeeded()
            .name(getDataSourceName())
            .build()
    }

    protected fun getRealmInstance(): Realm {
        return Realm.open(getRealmConfiguration())
    }

    protected fun closeRealmInstance(realm: Realm) {
        if (!realm.isClosed()) {
            realm.close()
        }
    }

    protected fun isDataSourceEmpty(model: KClass<out BaseRealmObject>): Boolean {
        return try {
            val realmInstance = getRealmInstance()
            val isDataSourceEmpty = realmInstance.query(model).count().find() <= 0
            closeRealmInstance(realmInstance)
            isDataSourceEmpty
        } catch (ex: Exception) {
            println("Error : ${ex.message}")
            ex.printStackTrace()
            true
        }
    }

    abstract fun getSchema(): Set<KClass<out BaseRealmObject>>

    abstract fun getDataSourceName(): String

}
