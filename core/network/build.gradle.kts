plugins {
    id("foodmarket.android.library")
    id("foodmarket.android.hilt")
}

android {
    namespace = "com.rezyfr.foodmarket.core.network"
}
dependencies {
    implementation(project(":core:domain"))

    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.converterGson)
    implementation(libs.okhttp.okhttp)
    implementation(libs.okhttp.logging)
}
