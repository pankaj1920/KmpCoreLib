import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(libs.versions.javaVersion.v21.get()))
        }
        task("testClasses")
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "networking"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation (libs.ktor.client.android.engine)
            implementation (libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
            implementation(libs.ktor.client.ios.darwin)
        }
        commonMain.dependencies {
            implementation(libs.kotlin.serialization)

            // ktor
            implementation (libs.ktor.client.core)
            implementation(libs.ktor.client.serializer)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging) // enable http logging
            implementation(libs.ktor.json.serializer)
            implementation(projects.kmpCoreLib.utils)
            implementation(projects.kmpCoreLib.di)
            implementation(projects.kmpCoreLib.storage.datastore)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.psbapp.networking"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.v21.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.v21.get())
    }
}
