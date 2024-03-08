plugins {
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {
    implementation(Libs.Dagger.dagger)
    kapt(Libs.Dagger.annotationProcessor)
    implementation(Libs.Kotlin.coroutinesCore)
    implementation(Libs.Kotlin.coroutinesAndroid)
    testImplementation(TestLibs.UnitTest.junit4)
}
