plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.yazantarifi.coina.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.yazantarifi.coina.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        renderscriptSupportModeEnabled = true
        renderscriptTargetApi = 26
        multiDexEnabled = true
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha08"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":androidCore"))
    implementation(platform("com.google.firebase:firebase-bom:30.0.1"))

    addJetpackComposeDependencies(this)
    addMaterialConfiguration(this)
    addFirebaseConfiguration(this)
    addApplicationDependencies(this)

    implementation("androidx.lifecycle:lifecycle-viewmodel:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
}

fun addJetpackComposeDependencies(configuration: DependencyHandlerScope) {
    configuration.implementation("androidx.core:core-ktx:1.7.0")
    configuration.implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    configuration.implementation("androidx.activity:activity-compose:1.4.0")
    configuration.implementation("androidx.compose.ui:ui:1.2.0")
    configuration.implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
    configuration.debugImplementation("androidx.compose.ui:ui-tooling:1.2.0")
    configuration.implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
    configuration.implementation("androidx.navigation:navigation-compose:2.4.2")
    configuration.implementation("com.google.accompanist:accompanist-pager:0.24.8-beta")
    configuration.implementation("com.google.accompanist:accompanist-pager-indicators:0.24.8-beta")
    configuration.implementation("io.coil-kt:coil-compose:2.0.0")
    configuration.implementation("androidx.core:core-animation:1.0.0-beta01")
    configuration.implementation("androidx.compose.material3:material3:1.0.0-alpha03")
    configuration.implementation("androidx.compose.material3:material3:1.0.0-alpha11")
    configuration.implementation("androidx.compose.material3:material3-window-size-class:1.0.0-alpha11")
    configuration.implementation("androidx.compose.material3:material3:1.0.0-alpha11")
    configuration.implementation("com.google.accompanist:accompanist-systemuicontroller:0.24.13-rc")
    configuration.implementation("com.google.android.material:material:1.8.0-alpha01")
    configuration.implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha02")
}


fun addMaterialConfiguration(configuration: DependencyHandlerScope) {
    configuration.implementation("androidx.compose.material3:material3:1.0.0-alpha11")
    configuration.implementation("androidx.compose.material3:material3-window-size-class:1.0.0-alpha11")
    configuration.implementation("androidx.compose.material3:material3:1.0.0-alpha11")
    configuration.implementation("androidx.compose.animation:animation:1.1.1")
    configuration.implementation("androidx.compose.animation:animation-core:1.1.1")
}

fun addApplicationDependencies(configuration: DependencyHandlerScope) {
    configuration.implementation("com.jakewharton.timber:timber:5.0.1")
    configuration.implementation("androidx.multidex:multidex:2.0.1")
    configuration.implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
}


fun addFirebaseConfiguration(configuration: DependencyHandlerScope) {
    configuration.implementation("com.google.firebase:firebase-functions-ktx")
    configuration.implementation("com.google.firebase:firebase-messaging")
    configuration.implementation("com.google.firebase:firebase-config")
    configuration.implementation("com.google.firebase:firebase-crashlytics-ktx")
    configuration.implementation("com.google.firebase:firebase-analytics-ktx")
    configuration.implementation("com.google.firebase:firebase-perf-ktx")
}
