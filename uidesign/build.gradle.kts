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
            baseName = "uidesign"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            api(compose.preview)
            api(libs.androidx.activity.compose)
            api(libs.koin.android)
        }
        commonMain.dependencies {
            //put your multiplatform dependencies here
            api(compose.components.resources)
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.ui)
            api(compose.components.uiToolingPreview)
            api(libs.androidx.lifecycle.viewmodel)
            api(libs.androidx.lifecycle.runtime.compose)
            api(libs.sdp.ssp.compose.multiplatform)
            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.kotlin.serialization)
            api(projects.core.resources)
            api(projects.core.utils)
            api(projects.core.extension)

            implementation("com.kizitonwose.calendar:compose-multiplatform:2.6.1")

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.psbapp.uidesign"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.v21.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.v21.get())
    }
}
