import Versions.kotlinVersion

object Dependencies {
    object GradlePlugin {
        val android = "com.android.tools.build:gradle:3.6.3"
        val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object Kotlin {
        val lib = "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7"
    }

    object AndroidX {
        val appCompat = "androidx.appcompat:appcompat:1.1.0"
        val activityKtx = "androidx.activity:activity-ktx:1.1.0"
        val core = "androidx.core:core-ktx:1.2.0"
        val constraintlayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.4"
        val lifecycleVer = "2.2.0"
        val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVer"
        val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVer"
        val materialDesign = "com.google.android.material:material:1.2.0-beta01"
        val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
        private val roomVer = "2.2.5"
        val room = "androidx.room:room-runtime:$roomVer"
        val roomCompiler = "androidx.room:room-compiler:$roomVer"
        val roomKtx = "androidx.room:room-ktx:$roomVer"
    }

    object Dagger {
        private val ver = "2.27"
        val core = "com.google.dagger:dagger:$ver"
        val compiler = "com.google.dagger:dagger-compiler:$ver"
        val android = "com.google.dagger:dagger-android:$ver"
        val androidSupport = "com.google.dagger:dagger-android-support:$ver"
        val processor = "com.google.dagger:dagger-android-processor:$ver"
    }
}