buildscript {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
        maven { url = uri("https://kotlin.bintray.com/kotlin-eap") }
        maven { url = uri("https://kotlin.bintray.com/kotlin-dev") }
        maven { url = uri("https://dl.bintray.com/sandwwraith/libs-preview/") }
    }

    dependencies {
        classpath("com.google.gms:google-services:4.3.14")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.6.0-alpha04")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.0-alpha05").apply(false)
    kotlin("android").version("1.6.20").apply(false)
    id("com.google.dagger.hilt.android") version "2.44" apply false
    kotlin("multiplatform").version("1.6.20").apply(false)
    kotlin("jvm").version("1.6.20")
    kotlin("plugin.serialization").version("1.6.20")
    id("com.android.library") version "8.0.0-alpha05" apply false
//    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
//    id("com.android.library").version("8.0.0-alpha05").apply(false)
}

//
//tasks.register("clean", Delete::class) {
//    delete(rootProject.buildDir)
//}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}