plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(project(":data:db"))
    implementation(project(":data:repository"))

    implementation(Dependencies.Kotlin.lib)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.activityKtx)
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.constraintlayout)
    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(Dependencies.AndroidX.lifecycle)
    implementation(Dependencies.AndroidX.navigation)
    implementation(Dependencies.AndroidX.navigationUi)
    implementation(Dependencies.AndroidX.recyclerview)
    implementation(Dependencies.Dagger.core)
    kapt(Dependencies.Dagger.compiler)
    implementation(Dependencies.Dagger.android)
    kapt(Dependencies.Dagger.processor)
}
