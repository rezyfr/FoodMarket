plugins {
    id("foodmarket.android.library")
    id("foodmarket.android.hilt")
}

android {
    namespace = "com.rezyfr.foodmarket.core.network"
}
dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:persistence"))

    implementation(libs.retrofit.retrofit)
    api(libs.retrofit.converterGson)
    implementation(libs.okhttp.okhttp)
    implementation(libs.okhttp.logging)
}
