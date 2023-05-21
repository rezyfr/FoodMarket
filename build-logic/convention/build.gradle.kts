plugins {
    `kotlin-dsl`
}


gradlePlugin {
    plugins {
        register("androidFeature") {
            id = "foodmarket.android.feature"
            implementationClass = "AndroidFeaturePlugin"
        }
        register("androidLibrary") {
            id = "foodmarket.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("androidHilt") {
            id = "foodmarket.android.hilt"
            implementationClass = "AndroidHiltPlugin"
        }
        register("androidLibraryCompose") {
            id = "foodmarket.android.library.compose"
            implementationClass = "AndroidLibraryComposePlugin"
        }
        register("androidApplication") {
            id = "foodmarket.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidApplicationCompose") {
            id = "foodmarket.android.application.compose"
            implementationClass = "AndroidApplicationComposePlugin"
        }
    }
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}
