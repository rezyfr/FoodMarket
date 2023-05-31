plugins {
    alias(libs.plugins.ksp)
    id("foodmarket.android.feature")
    id("foodmarket.android.library.compose")
}

android {
    namespace = "com.rezyfr.foodmarket.feature.dashboard"
}

dependencies {
    implementation(project(":domain:food"))
    implementation(project(":domain:auth"))

    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.navigation.material)
}
