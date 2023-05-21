plugins {
    alias(libs.plugins.ksp)
    id("foodmarket.android.library")
    id("foodmarket.android.hilt")
}

android {
    namespace = "com.rezyfr.foodmarket.data.auth"
}