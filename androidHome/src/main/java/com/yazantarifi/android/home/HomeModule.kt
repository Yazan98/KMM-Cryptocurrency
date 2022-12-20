package com.yazantarifi.android.home

import com.yazantarifi.coina.database.CategoriesDataSource
import com.yazantarifi.coina.database.CoinsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun getCoinsDataSource(): CoinsDataSource {
        return CoinsDataSource()
    }

    @Provides
    @Singleton
    fun getCategoriesDataSource(): CategoriesDataSource {
        return CategoriesDataSource()
    }

}