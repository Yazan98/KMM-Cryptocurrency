package com.yazantarifi.coina.android;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0014H\u0007J\u0012\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\u0017\u001a\u00020\u0018H\u0007\u00a8\u0006\u0019"}, d2 = {"Lcom/yazantarifi/coina/android/ApplicationModule;", "", "()V", "getAuthUseCase", "Lcom/yazantarifi/coina/useCases/AuthUseCase;", "apiManager", "Lcom/yazantarifi/coina/api/requests/ApplicationApiManager;", "database", "Lcom/yazantarifi/coina/database/CoinsDataSource;", "getCoinInfoUseCase", "Lcom/yazantarifi/coina/useCases/CoinInfoUseCase;", "getGetCategoriesUseCase", "Lcom/yazantarifi/coina/useCases/GetCategoriesUseCase;", "Lcom/yazantarifi/coina/database/CategoriesDataSource;", "getGetCategoryCoinsUseCase", "Lcom/yazantarifi/coina/useCases/GetCategoryCoinsUseCase;", "getGetCoinsUseCase", "Lcom/yazantarifi/coina/useCases/GetCoinsUseCase;", "getGetExchangesUseCase", "Lcom/yazantarifi/coina/useCases/GetExchangesUseCase;", "Lcom/yazantarifi/coina/database/ExchangesDataSource;", "getStorageProviderImplementationInstance", "Lcom/yazantarifi/coina/context/CoinaStorageProvider;", "context", "Landroid/content/Context;", "androidApp_debug"})
@dagger.Module
public final class ApplicationModule {
    @org.jetbrains.annotations.NotNull
    public static final com.yazantarifi.coina.android.ApplicationModule INSTANCE = null;
    
    private ApplicationModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Provides
    public final com.yazantarifi.coina.context.CoinaStorageProvider getStorageProviderImplementationInstance(@org.jetbrains.annotations.NotNull
    @dagger.hilt.android.qualifiers.ApplicationContext
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Provides
    public final com.yazantarifi.coina.useCases.AuthUseCase getAuthUseCase(@org.jetbrains.annotations.NotNull
    com.yazantarifi.coina.api.requests.ApplicationApiManager apiManager, @org.jetbrains.annotations.NotNull
    com.yazantarifi.coina.database.CoinsDataSource database) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Provides
    public final com.yazantarifi.coina.useCases.CoinInfoUseCase getCoinInfoUseCase(@org.jetbrains.annotations.NotNull
    com.yazantarifi.coina.api.requests.ApplicationApiManager apiManager) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Provides
    public final com.yazantarifi.coina.useCases.GetCategoriesUseCase getGetCategoriesUseCase(@org.jetbrains.annotations.NotNull
    com.yazantarifi.coina.api.requests.ApplicationApiManager apiManager, @org.jetbrains.annotations.NotNull
    com.yazantarifi.coina.database.CategoriesDataSource database) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Provides
    public final com.yazantarifi.coina.useCases.GetCategoryCoinsUseCase getGetCategoryCoinsUseCase(@org.jetbrains.annotations.NotNull
    com.yazantarifi.coina.api.requests.ApplicationApiManager apiManager) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Provides
    public final com.yazantarifi.coina.useCases.GetExchangesUseCase getGetExchangesUseCase(@org.jetbrains.annotations.NotNull
    com.yazantarifi.coina.api.requests.ApplicationApiManager apiManager, @org.jetbrains.annotations.NotNull
    com.yazantarifi.coina.database.ExchangesDataSource database) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Provides
    public final com.yazantarifi.coina.useCases.GetCoinsUseCase getGetCoinsUseCase(@org.jetbrains.annotations.NotNull
    com.yazantarifi.coina.api.requests.ApplicationApiManager apiManager, @org.jetbrains.annotations.NotNull
    com.yazantarifi.coina.database.CoinsDataSource database) {
        return null;
    }
}