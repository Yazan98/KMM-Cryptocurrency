package com.yazantarifi.coina.android

import android.content.Context
import com.yazantarifi.android.auth.useCases.AuthUseCase
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.context.CoinaContext
import com.yazantarifi.coina.context.CoinaStorageKeyValue
import com.yazantarifi.coina.context.CoinaStorageProvider
import com.yazantarifi.coina.database.CoinsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun getStorageProviderImplementationInstance(@ApplicationContext context: Context): CoinaStorageProvider {
        return CoinaStorageProvider(CoinaStorageKeyValue(context.applicationContext as CoinaContext))
    }

    @Provides
    @Singleton
    fun getAuthUseCase(apiManager: ApplicationApiManager, database: CoinsDataSource): AuthUseCase {
        return AuthUseCase(apiManager, database)
    }

}
