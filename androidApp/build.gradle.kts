plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-android")
}
group = "ru.rsue.borisov.thecloudmultiplatform"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
    maven(url = "https://jitpack.io/")
}
dependencies {
    implementation(project(":shared"))
    implementation ("com.squareup.retrofit2:converter-scalars:2.6.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9-native-mt-2")
    implementation ("com.squareup.okhttp3:okhttp:3.12.12")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.12.12")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("com.github.hv0rost:TheCloudServerLibrary:0.1.3")
    implementation("com.squareup.retrofit2:retrofit:2.6.2")
    implementation("com.squareup.retrofit2:converter-gson:2.6.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.annotation:annotation:1.1.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.1")
    //implementation ("androidx.recyclerview:recyclerview:1.2.0")
}
android {

    //useLibrary("org.apache.http.legacy")

    compileSdkVersion(30)
    defaultConfig {
        applicationId = "ru.rsue.borisov.thecloudmultiplatform.androidApp"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    packagingOptions {
        pickFirst("META-INF/INDEX.LIST")
        pickFirst("META-INF/io.netty.versions.properties")
    }
}

