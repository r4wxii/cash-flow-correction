plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    appConfig()
    commonConfig()

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":component"))
    implementation(project(":data:db"))
    implementation(project(":data:repository"))
    implementation(project(":domain:model"))
    implementation(project(":domain:usecase"))
    implementation(project(":features:accountbook"))

    implementation(Dependencies.Kotlin.lib)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.activityKtx)
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.constraintlayout)
    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(Dependencies.AndroidX.navigationFragment)
    implementation(Dependencies.AndroidX.navigationUi)
    implementation(Dependencies.Dagger.core)
    kapt(Dependencies.Dagger.compiler)
    implementation(Dependencies.Dagger.android)
    implementation(Dependencies.Dagger.androidSupport)
    kapt(Dependencies.Dagger.processor)
    implementation(Dependencies.Dagger.hilt)
    kapt(Dependencies.Dagger.hiltCompiler)
}
