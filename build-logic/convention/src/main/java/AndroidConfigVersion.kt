
object AppVersion {
    const val major = 0
    const val minor = 0
    const val patch = 1
    const val subPatch =  0
}

object AndroidConfigVersion {
    const val compileSdk = 33
    const val buildTools = "31.0.0"
    const val minSdk = 24
    const val targetSdk = 33
    const val versionCode = AppVersion.major * 1_000_000 + AppVersion.minor * 1000 + AppVersion.patch * 10 + AppVersion.subPatch
    const val versionName = "${AppVersion.major}.${AppVersion.minor}.${AppVersion.patch}"
}