pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Food Market"
include(":app")
include(":feature:auth")
include(":domain:auth")
include(":data:auth")

include(":core:network")
include(":core:ui")
include(":core:domain")
 