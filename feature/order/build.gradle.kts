plugins {
    alias(libs.plugins.ksp)
    id("foodmarket.android.feature")
    id("foodmarket.android.library.compose")
}

android {
    namespace = "com.rezyfr.foodmarket.feature.order"
}

dependencies {
    implementation(project(":domain:food"))
    implementation(project(":domain:auth"))
    implementation(project(":domain:order"))

    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.navigation.material)
}
