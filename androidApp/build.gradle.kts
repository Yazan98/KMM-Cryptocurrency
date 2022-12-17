plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
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

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/license.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/notice.txt"
            excludes += "META-INF/ASL2.0"
            excludes += "androidsupportmultidexversion.txt"
            exclude("META-INF/*.kotlin_module")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
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
            isDebuggable = false
            isShrinkResources = true
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
            versionNameSuffix = ".production"
        }

        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            isShrinkResources = false
            isJniDebuggable = true
            isRenderscriptDebuggable = true
            renderscriptOptimLevel = 3
        }
    }

}

dependencies {
    implementation(project(":shared"))
    implementation(project(":androidCore"))
    implementation(project(":androidHome"))
    implementation(project(":androidAuth"))
    implementation(platform("com.google.firebase:firebase-bom:30.0.1"))

    addJetpackComposeDependencies(this)
    addMaterialConfiguration(this)
    addFirebaseConfiguration(this)
    addApplicationDependencies(this)
    setupHiltConfigurations(this)

    implementation("androidx.lifecycle:lifecycle-viewmodel:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    implementation("io.ktor:ktor-client-core:2.2.1")
    implementation("io.ktor:ktor-client-android:2.2.1")
}


fun setupHiltConfigurations(configurations: DependencyHandlerScope) {
    configurations.implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    configurations.implementation("com.google.dagger:hilt-android:2.44")
    configurations.kapt("com.google.dagger:hilt-android-compiler:2.44")
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
