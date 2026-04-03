import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    dependencies {
        implementation(projects.shared)
        implementation(compose.desktop.currentOs)
        implementation(libs.compose.components.resources)
        implementation(libs.kotlinx.coroutines.swing)

        implementation(platform(libs.koin.bom))
        implementation(libs.koin.core)
    }
}


compose.desktop {
    application {
        mainClass = "com.techullurgy.dosthman.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.techullurgy.dosthman"
            packageVersion = "1.0.0"
        }
    }
}

compose.resources {
    packageOfResClass = "com.techullurgy.dosthman.generated.resources.desktop"
}