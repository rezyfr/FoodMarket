plugins {
    alias(libs.plugins.ksp)
    id("foodmarket.android.library")
    id("foodmarket.android.hilt")
}

android {
    namespace = "com.rezyfr.foodmarket.data.auth"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:persistence"))
    implementation(project(":domain:auth"))
    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.converterGson)
}