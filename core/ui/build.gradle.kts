plugins {
    id("foodmarket.android.library")
    id("foodmarket.android.library.compose")
}

android {
    namespace = "com.rezyfr.foodmarket.core.ui"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material.material)
    api(libs.androidx.compose.ui.googlefonts)

    debugApi(libs.androidx.compose.ui.tooling)
}