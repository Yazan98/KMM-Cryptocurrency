package com.yazantarifi.coina.android

import android.content.Context
import com.yazantarifi.coina.useCases.AuthUseCase
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import com.yazantarifi.coina.context.CoinaContext
import com.yazantarifi.coina.context.CoinaStorageKeyValue
import com.yazantarifi.coina.context.CoinaStorageProvider
import com.yazantarifi.coina.database.CategoriesDataSource
import com.yazantarifi.coina.database.CoinsDataSource
import com.yazantarifi.coina.database.ExchangesDataSource
import com.yazantarifi.coina.useCases.CoinInfoUseCase
import com.yazantarifi.coina.useCases.GetCategoriesUseCase
import com.yazantarifi.coina.useCases.GetCategoryCoinsUseCase
import com.yazantarifi.coina.useCases.GetCoinsUseCase
import com.yazantarifi.coina.useCases.GetExchangesUseCase
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
        return AuthUseCase().apply {
            addDependencies(apiManager, database)
        }
    }

    @Provides
    @Singleton
    fun getCoinInfoUseCase(apiManager: ApplicationApiManager): CoinInfoUseCase {
        return CoinInfoUseCase().addDependencies(apiManager)
    }

    @Provides
    @Singleton
    fun getGetCategoriesUseCase(apiManager: ApplicationApiManager, database: CategoriesDataSource): GetCategoriesUseCase {
        return GetCategoriesUseCase().addDependencies(apiManager, database)
    }

    @Provides
    @Singleton
    fun getGetCategoryCoinsUseCase(apiManager: ApplicationApiManager): GetCategoryCoinsUseCase {
        return GetCategoryCoinsUseCase().addDependencies(apiManager)
    }

    @Provides
    @Singleton
    fun getGetExchangesUseCase(apiManager: ApplicationApiManager, database: ExchangesDataSource): GetExchangesUseCase {
        return GetExchangesUseCase().addDependencies(apiManager, database)
    }

    @Provides
    @Singleton
    fun getGetCoinsUseCase(apiManager: ApplicationApiManager, database: CoinsDataSource): GetCoinsUseCase {
        return GetCoinsUseCase().addDependencies(apiManager, database)
    }

}
