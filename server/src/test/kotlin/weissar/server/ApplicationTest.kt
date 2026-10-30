package weissar.server

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ApplicationTest {

    @Test
    fun healthCheck() = testApplication {
        application { module() }
        assertEquals("OK", client.get("/health").bodyAsText())
    }

    @Test
    fun servesWeb() = testApplication {
        application { module() }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertTrue(response.bodyAsText().contains("web.js"))
        assertEquals(HttpStatusCode.OK, client.get("/web.js").status)
        assertEquals(HttpStatusCode.OK, client.get("/university-delete-account-request.html").status)
    }
}
