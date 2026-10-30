plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "weissar"
version = "1.0.0"

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("weissar.server.ApplicationKt")
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.compression)
    implementation(libs.ktor.server.caching.headers)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.logback.classic)

    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test)
}

// Built web (Compose HTML -> JS) is bundled into the server jar under /static
val webDistribution = configurations.create("webDistribution") {
    isCanBeConsumed = false
}

dependencies {
    webDistribution(project(path = ":web", configuration = "webDistribution"))
}

tasks.processResources {
    from(webDistribution) {
        into("static")
    }
}
