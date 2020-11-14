buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
        maven(url = "https://jitpack.io/")
    }

    val kotlinVersion = "1.4.0"
    val sqlDelightVersion: String by project

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
        //classpath("io.ktor:ktor-client-core-jvm:1.4.0")
    }
}
group = "ru.rsue.borisov.thecloudmultiplatform"
version = "1.0-SNAPSHOT"


/*
allprojects {
    repositories {

        google()
        mavenCentral()
        jcenter()
        maven(url = "https://jitpack.io/")
    }
}
*/







