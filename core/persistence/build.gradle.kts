plugins {
    id("foodmarket.android.library")
    id("foodmarket.android.hilt")
}

android {
    namespace = "com.rezyfr.foodmarket.core.persistence"
}
dependencies {
    implementation(project(":core:domain"))

    api(libs.androidx.dataStore.preferences)
}
