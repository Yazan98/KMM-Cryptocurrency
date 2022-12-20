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

    abstract fun getSchema(): Set<KClass<out BaseRealmObject>>

    abstract fun getDataSourceName(): String

}
