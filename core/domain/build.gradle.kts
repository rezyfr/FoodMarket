plugins {
    alias(libs.plugins.ksp)
    id("foodmarket.android.library")
}

android {
    namespace = "com.rezyfr.foodmarket.core.domain"
}

dependencies {
    api(libs.kotlinx.coroutines.core)
}