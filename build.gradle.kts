plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.0-alpha05").apply(false)
    id("com.android.library").version("8.0.0-alpha05").apply(false)
    kotlin("android").version("1.6.20").apply(false)
    kotlin("multiplatform").version("1.6.20").apply(false)
    kotlin("jvm").version("1.6.20")
    kotlin("plugin.serialization").version("1.6.20")
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