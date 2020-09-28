plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
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
    implementation(project(":domain:model"))

    implementation(Dependencies.Kotlin.coroutines)

    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.constraintlayout)
    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(Dependencies.AndroidX.liveData)
    implementation(Dependencies.AndroidX.materialDesign)
    implementation(Dependencies.AndroidX.navigationFragment)
    implementation(Dependencies.AndroidX.navigationUi)
    implementation(Dependencies.AndroidX.recyclerview)
    implementation(Dependencies.AndroidX.ViewModel)
    implementation(Dependencies.Dagger.core)
    kapt(Dependencies.Dagger.compiler)
    implementation(Dependencies.Dagger.android)
    implementation(Dependencies.Dagger.androidSupport)
    kapt(Dependencies.Dagger.processor)
}
