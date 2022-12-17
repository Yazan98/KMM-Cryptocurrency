plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.yazantarifi.android.core"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
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
    addJetpackComposeDependencies(this)
    addMaterialConfiguration(this)

    implementation("androidx.lifecycle:lifecycle-viewmodel:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
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
