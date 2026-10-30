package weissar.server

import io.ktor.http.CacheControl
import io.ktor.http.ContentType
import io.ktor.http.content.CachingOptions
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.staticResources
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.cachingheaders.CachingHeaders
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.plugins.compression.Compression
import io.ktor.server.plugins.compression.deflate
import io.ktor.server.plugins.compression.gzip
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun main() {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080
    embeddedServer(Netty, port = port, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(CallLogging)
    install(Compression) {
        gzip()
        deflate()
    }
    install(CachingHeaders) {
        options { _, content ->
            when (content.contentType?.withoutParameters()) {
                ContentType.Text.Html -> CachingOptions(CacheControl.NoCache(null))
                else -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 60 * 60, visibility = CacheControl.Visibility.Public))
            }
        }
    }

    routing {
        get("/health") {
            call.respondText("OK")
        }

        // Web built from :web module, see server/build.gradle.kts
        staticResources("/", "static")
    }
}
