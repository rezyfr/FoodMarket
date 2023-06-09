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
include(":domain:food")
include(":data:food")
include(":feature:dashboard")
include(":feature:order")
include(":domain:order")
include(":data:order")
include(":feature:profile")

include(":core:network")
include(":core:ui")
include(":core:domain")
include(":core:persistence")
 