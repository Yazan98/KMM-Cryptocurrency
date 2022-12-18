package com.yazantarifi.android.home

import com.yazantarifi.coina.database.CoinExchangesDataSource
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
    fun getCoinExchangesDataSource(): CoinExchangesDataSource {
        return CoinExchangesDataSource()
    }

}