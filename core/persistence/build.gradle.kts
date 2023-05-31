plugins {
    id("foodmarket.android.library")
    id("foodmarket.android.hilt")
}

android {
    namespace = "com.rezyfr.foodmarket.core.persistence"
}
dependencies {
    implementation(project(":core:domain"))

    implementation(libs.androidx.dataStore.preferences)
}
