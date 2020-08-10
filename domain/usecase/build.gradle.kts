plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    commonConfig()

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":domain:model"))

    implementation(Dependencies.Kotlin.coroutines)

    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.Dagger.core)
    kapt(Dependencies.Dagger.compiler)
    implementation(Dependencies.Dagger.android)
    kapt(Dependencies.Dagger.processor)
}
