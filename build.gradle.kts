buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.3")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
        classpath("com.google.gms:google-services:4.4.1")
    }
}
plugins {
    id("com.android.application") version Versions.gradle apply false
    id("com.android.library") version Versions.gradle apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("com.google.dagger.hilt.android") version Versions.hiltAndroid apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

