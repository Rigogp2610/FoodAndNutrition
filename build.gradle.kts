// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.detekt) version "1.23.6"
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        config.setFrom("$rootDir/config/detekt/detekt.yml")

        dependencies {
            detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6")
        }
    }
}

true // Needed to make the Suppress annotation work for the plugins block