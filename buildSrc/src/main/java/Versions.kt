object Versions {
    const val kotlinVersion = "1.4.30"
    const val targetSdkVersion = 29
    const val compileSdkVersion = 29
    const val minSdkVersion = 28

    private const val versionMajor = 0
    private const val versionMinor = 0
    private const val versionPatch = 1
    private const val versionOffset = 0
    const val androidVersionCode =
        (versionMajor * 10000 + versionMinor * 100 + versionPatch) * 100 + versionOffset

    const val androidVersionName = "$versionMajor.$versionMinor.$versionPatch"
}