package com.yazantarifi.android.core

import com.yazantarifi.coina.api.HttpBaseClient
import com.yazantarifi.coina.api.requests.ApplicationApiManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun getApplicationApiManager(): ApplicationApiManager {
        return ApplicationApiManager(HttpBaseClient().httpClient)
    }

}
