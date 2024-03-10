plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}


android {
    namespace = "com.gilberto.test"
    compileSdk = AndroidConfigs.compileSdkVersion

    defaultConfig {
        multiDexEnabled = true
        applicationId = AndroidConfigs.APLICATION_ID
        versionCode = AndroidConfigs.versionCode
        versionName = AndroidConfigs.versionName

        minSdk = AndroidConfigs.minSdkVersion
        targetSdk = AndroidConfigs.targetSdkVersion
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }


    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Libs.Kotlin.stdLib)
    implementation(Libs.Kotlin.coroutinesCore)
    implementation(Libs.Kotlin.coroutinesAndroid)

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.lifecycleExt)

    implementation(Libs.AndroidX.activityCompose)
    implementation(Libs.AndroidX.navigationCompose)
    implementation(Libs.AndroidX.hiltNavigationCompose)
    implementation(Libs.AndroidX.coreSplashScreen)

    implementation(Libs.AndroidX.cameraCamera2)
    implementation(Libs.AndroidX.cameraVideo)
    implementation(Libs.AndroidX.cameraCore)
    implementation(Libs.AndroidX.cameraExtensions)
    implementation(Libs.AndroidX.cameraView)
    implementation(Libs.AndroidX.cameraLifecycle)
    implementation(Libs.AndroidX.concurrent)

    implementation(Libs.AndroidX.material3)
    implementation(Libs.AndroidX.uiToolingPreview)
    implementation(Libs.AndroidX.uiTooling)
    implementation(Libs.Network.sandwich)
    implementation(Libs.AndroidX.materialIconsExtended)

    implementation(Libs.Hilt.hiltAndroid)

    kapt(Libs.Hilt.hiltCompiler)
    kapt(Libs.Hilt.hiltAndroidCompiler)
    implementation(Libs.ThirdPartyLib.permissions)
    implementation(Libs.ThirdPartyLib.coilKt)
    implementation(Libs.ThirdPartyLib.timber)
    implementation(Libs.ThirdPartyLib.guavaAndroid)

    testImplementation(TestLibs.UnitTest.junit4)
}