import com.android.build.gradle.BaseExtension

fun BaseExtension.appConfig() {
    defaultConfig {
        applicationId = "com.r4wxii.cashflowcorrection"
        versionCode = Versions.androidVersionCode
        versionName = Versions.androidVersionName
    }
}

fun BaseExtension.commonConfig() {
    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion = "29.0.2"

    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}