package com.yazantarifi.coina.database

import com.yazantarifi.coina.database.models.RealmCategory
import com.yazantarifi.coina.models.Category
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.types.BaseRealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

class CategoriesDataSource : CoinaBaseDataSource() {

    suspend fun writeCategoriesData(categories: ArrayList<Category>) {
        withContext(Dispatchers.Default) {
            val realmInstance = getRealmInstance()
            realmInstance.writeBlocking {
                categories.forEach { category ->
                    try {
                        copyToRealm(Category.toRealmCategory(category), UpdatePolicy.ALL)
                    } catch (ex: Exception) {
                        println("Exception While Writing Category")
                        ex.printStackTrace()
                    }
                }
            }
            closeRealmInstance(realmInstance)
        }
    }

    fun getCategories(): ArrayList<Category> {
        val realmInstance = getRealmInstance()
        val categories = ArrayList<Category>()
        realmInstance.query(RealmCategory::class).find().forEach {
            categories.add(RealmCategory.toCategory(it))
        }
        closeRealmInstance(realmInstance)
        return categories
    }

    fun isDataSourceEmpty(): Boolean {
        return isDataSourceEmpty(RealmCategory::class)
    }

    override fun getSchema(): Set<KClass<out BaseRealmObject>> {
        return setOf(RealmCategory::class)
    }

    override fun getDataSourceName(): String {
        return "categories.realm"
    }
}