plugins {
    alias(libs.plugins.ksp)
    id("foodmarket.android.feature")
    id("foodmarket.android.library.compose")
}

android {
    namespace = "com.rezyfr.foodmarket.feature.auth"

    ksp {
        arg("compose-destinations.moduleName", "auth")
        arg("compose-destinations.mode", "destinations")
    }
}
