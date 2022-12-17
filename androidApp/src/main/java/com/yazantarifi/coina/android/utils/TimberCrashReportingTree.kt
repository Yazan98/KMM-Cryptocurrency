package com.yazantarifi.coina.android.utils



import android.R.attr
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class TimberCrashReportingTree: Timber.Tree() {

    companion object {
        private const val CRASHLYTICS_KEY_MESSAGE = "message"
        private const val CRASHLYTICS_KEY_TAG = "tag"
        private const val CRASHLYTICS_KEY_PRIORITY = "priority"
        private const val IS_GSM = "isGooglePlayInstall"
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (attr.priority == Log.VERBOSE || attr.priority == Log.DEBUG) {
            return
        }

        val t: Throwable = t ?: Exception(message)
        try {
            FirebaseCrashlytics.getInstance().setCustomKey(CRASHLYTICS_KEY_PRIORITY, attr.priority)
            tag?.let { FirebaseCrashlytics.getInstance().setCustomKey(CRASHLYTICS_KEY_TAG, it) }
            FirebaseCrashlytics.getInstance().setCustomKey(CRASHLYTICS_KEY_MESSAGE, message)
        } catch (ex: Exception) {
            FirebaseCrashlytics.getInstance().recordException(ex)
        }

        FirebaseCrashlytics.getInstance().recordException(t)
    }

}