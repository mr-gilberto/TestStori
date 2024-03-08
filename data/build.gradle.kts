plugins {
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.data"
    compileSdk = AndroidConfigs.compileSdkVersion


    defaultConfig {
        multiDexEnabled = true

        minSdk = AndroidConfigs.minSdkVersion
        targetSdk = AndroidConfigs.targetSdkVersion
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {

    implementation(project(":domain"))

    implementation(Libs.Hilt.hiltAndroid)
    kapt(Libs.Hilt.hiltCompiler)
    kapt(Libs.Hilt.hiltAndroidCompiler)
    implementation(Libs.Dagger.dagger)
    kapt(Libs.Dagger.annotationProcessor)
    implementation(Libs.Kotlin.coroutinesCore)
    implementation(Libs.Kotlin.coroutinesAndroid)

    implementation(platform(Libs.ThirdPartyLib.firebaseBom))
    implementation(Libs.ThirdPartyLib.firebaseDatabase)
    implementation(Libs.ThirdPartyLib.firebaseStorage)
    implementation(Libs.ThirdPartyLib.firebaseAuth)
}

