import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.ksp)
    id("foodmarket.android.application")
    id("foodmarket.android.application.compose")
    id("foodmarket.android.hilt")
    alias(libs.plugins.kotlin.android)
}

fun buildProperty(key: String, format: Boolean = false): String {
    return if (format) {
        String.format("\"%s\"", project.property(key) as String)
    } else {
        project.property(key) as String
    }
}

android {
    namespace = "com.rezyfr.foodmarket"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions("env")
    productFlavors {
        create("dev") {
            dimension = "env"
            buildConfigField("String", "BASE_URL", buildProperty("BASE_URL_DEV"))
        }
        create("prod") {
            dimension = "env"
            buildConfigField("String", "BASE_URL", buildProperty("BASE_URL_PROD"))
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:network"))

    implementation(project(":feature:auth"))
    implementation(project(":data:auth"))
    implementation(project(":domain:auth"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.accompanist.navigation.animation)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    debugImplementation(libs.androidx.compose.ui.tooling)
}