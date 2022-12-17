package com.yazantarifi.coina.android

import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import com.yazantarifi.coina.android.utils.TimberCrashReportingTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CoinaApplication : MultiDexApplication(), Thread.UncaughtExceptionHandler {

    override fun onCreate() {
        super.onCreate()
        registerTimberConfiguration()
        initFirebaseConfiguration()
    }

    private fun initFirebaseConfiguration() {
        try {
            FirebaseApp.initializeApp(this)
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
            FirebaseCrashlytics.getInstance().sendUnsentReports()
            FirebaseMessaging.getInstance().isAutoInitEnabled = true
            FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(true)
        } catch (ex: Exception) {
            Timber.e(ex)
        }
    }

    private fun registerTimberConfiguration() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(TimberCrashReportingTree())
        }
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        Timber.e(e)
    }

}