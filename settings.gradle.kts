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
include(":androidFeatures:androidCore")
include(":androidFeatures:androidHome")
include(":androidFeatures:androidAuth")
