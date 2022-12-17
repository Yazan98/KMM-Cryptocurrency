package com.yazantarifi.coina.android;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0017R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\r"}, d2 = {"Lcom/yazantarifi/coina/android/MainScreen;", "Lcom/yazantarifi/android/core/BaseScreen;", "()V", "storage", "Lcom/yazantarifi/coina/context/CoinaStorageProvider;", "getStorage", "()Lcom/yazantarifi/coina/context/CoinaStorageProvider;", "setStorage", "(Lcom/yazantarifi/coina/context/CoinaStorageProvider;)V", "OnScreenContent", "", "savedInstanceState", "Landroid/os/Bundle;", "androidApp_debug"})
@dagger.hilt.android.AndroidEntryPoint
public class MainScreen extends com.yazantarifi.android.core.BaseScreen {
    @javax.inject.Inject
    public com.yazantarifi.coina.context.CoinaStorageProvider storage;
    
    public MainScreen() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.yazantarifi.coina.context.CoinaStorageProvider getStorage() {
        return null;
    }
    
    public final void setStorage(@org.jetbrains.annotations.NotNull
    com.yazantarifi.coina.context.CoinaStorageProvider p0) {
    }
    
    @androidx.compose.runtime.Composable
    @java.lang.Override
    public void OnScreenContent(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
}