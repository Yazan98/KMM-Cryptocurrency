pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
             url = uri("https://jitpack.io")
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}


rootProject.name = "Coina"
include(":androidApp")
include(":shared")
include(":androidFeatures:core")
include(":androidFeatures:home")
include(":androidFeatures:auth")
include(":androidFeatures:coin")
