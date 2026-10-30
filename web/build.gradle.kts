plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                outputFileName = "web.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
        }
    }
}

// Exposes built web so :server can bundle it
val webDistribution = configurations.create("webDistribution") {
    isCanBeResolved = false
}

artifacts {
    add(webDistribution.name, tasks.named("jsBrowserDistribution"))
}
