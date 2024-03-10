object Libs {
    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val lifecycleExt = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExt}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomAnnotationProcessor = "androidx.room:room-compiler:${Versions.room}"
        const val roomExtensions = "androidx.room:room-ktx:${Versions.room}"

        // Navigation

        const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"

        //compose
        const val material3 = "androidx.compose.material3:material3:${Versions.material3}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.datastore}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.uiTooling}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.materialIconsExtended}"
        const val materialComponents = "androidx.compose.material:material${Versions.materialComponents}"
        const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.navigationCompose}"
        const val uiText = "androidx.compose.ui:ui-text-android:${Versions.uiText}"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"

        //Core
        const val coreSplashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"

        //Camera
        const val cameraCamera2 = "androidx.camera:camera-camera2:${Versions.cameraX}"
        const val cameraCore = "androidx.camera:camera-core:${Versions.cameraX}"
        const val cameraExtensions = "androidx.camera:camera-extensions:${Versions.cameraX}"
        const val cameraLifecycle = "androidx.camera:camera-lifecycle:${Versions.cameraX}"
        const val cameraVideo = "androidx.camera:camera-video:${Versions.cameraX}"
        const val cameraView = "androidx.camera:camera-view:${Versions.cameraX}"

        const val concurrent = "androidx.concurrent:concurrent-futures-ktx:${Versions.concurrent}"

    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val annotationProcessor = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    }

    object Hilt {
        const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_alpha}"
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroid}"
    }

    object Network {
        object Retrofit {
            const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
            const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}"
        }

        object OkHttp {
            const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
            const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.httpLoggingInterceptor}"
        }

        object Moshi {
            const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
            const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
            const val moshiKotlinKapt = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
        }

        const val sandwich = "com.github.skydoves:sandwich:${Versions.sandwich}"
    }

    object ThirdPartyLib {
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val coilKt = "io.coil-kt:coil-compose:${Versions.coilKt}"
        const val realm = "io.realm.kotlin:library-base:${Versions.realm}"
        const val permissions = "com.google.accompanist:accompanist-permissions:${Versions.permissions}"
        const val crashlytics = "com.google.firebase:firebase-crashlytics:${Versions.crashlytics}"
        const val analytics = "com.google.firebase:firebase-analytics:${Versions.analytics}"
        const val guavaAndroid = "com.google.guava:guava:${Versions.guavaAndroid}"
        const val firebaseDatabase = "com.google.firebase:firebase-database-ktx"
        const val firestoreDatabase = "com.google.firebase:firebase-firestore"
        const val firebaseStorage = "com.google.firebase:firebase-storage-ktx"
        const val firebaseAuth = "com.google.firebase:firebase-auth-ktx"
        const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"

    }
}
