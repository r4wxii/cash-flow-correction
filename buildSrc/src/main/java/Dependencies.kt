import Versions.kotlinVersion

object Dependencies {
    object GradlePlugin {
        val android = "com.android.tools.build:gradle:4.1.0"
        val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${AndroidX.navigationVer}"
    }

    object Kotlin {
        val lib = "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2"
    }

    object AndroidX {
        val appCompat = "androidx.appcompat:appcompat:1.2.0"
        val activityKtx = "androidx.activity:activity-ktx:1.2.0"
        val core = "androidx.core:core-ktx:1.3.2"
        val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.0"
        val lifecycleVer = "2.3.0"
        val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVer"
        val navigationVer = "2.3.3"
        val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVer"
        val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVer"
        val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVer"
        val materialDesign = "com.google.android.material:material:1.3.0"
        val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
        private val roomVer = "2.2.6"
        val room = "androidx.room:room-runtime:$roomVer"
        val roomCompiler = "androidx.room:room-compiler:$roomVer"
        val roomKtx = "androidx.room:room-ktx:$roomVer"
    }

    object Dagger {
        private val ver = "2.32"
        val core = "com.google.dagger:dagger:$ver"
        val compiler = "com.google.dagger:dagger-compiler:$ver"
        val android = "com.google.dagger:dagger-android:$ver"
        val androidSupport = "com.google.dagger:dagger-android-support:$ver"
        val processor = "com.google.dagger:dagger-android-processor:$ver"
    }
}