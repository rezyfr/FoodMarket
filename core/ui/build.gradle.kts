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

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material.material)
    api(libs.androidx.compose.ui.googlefonts)

    debugApi(libs.androidx.compose.ui.tooling)
}