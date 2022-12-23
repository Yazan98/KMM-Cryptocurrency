pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}


rootProject.name = "Coina"
include(":androidApp")
include(":shared")
include(":androidFeatures:core")
include(":androidFeatures:home")
include(":androidFeatures:auth")
