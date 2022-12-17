package com.yazantarifi.coina.android;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2 = {"Lcom/yazantarifi/coina/android/ApplicationModule;", "", "()V", "getStorageProviderImplementationInstance", "Lcom/yazantarifi/coina/context/CoinaStorageProvider;", "context", "Landroid/content/Context;", "androidApp_debug"})
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
}