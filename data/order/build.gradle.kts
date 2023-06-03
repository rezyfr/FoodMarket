plugins {
    alias(libs.plugins.ksp)
    id("foodmarket.android.library")
    id("foodmarket.android.hilt")
}

android {
    namespace = "com.rezyfr.foodmarket.data.order"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":domain:order"))
    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.converterGson)
}