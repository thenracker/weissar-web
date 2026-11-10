package weissar.web.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import weissar.web.components.Body
import weissar.web.components.Bullet
import weissar.web.components.Content
import weissar.web.components.FullSection
import weissar.web.components.Icon
import weissar.web.components.Spacer
import weissar.web.components.Subtitle
import weissar.web.components.Title
import weissar.web.i18n.tr
import weissar.web.theme.AppStyles
import weissar.web.theme.Colors

/** What I can build for clients – three columns right after the intro. */
@Composable
fun ServicesSection() {
    FullSection(id = "services", background = Colors.backgroundLight) {
        Content {
            Title(tr("Co dělám", "What I do"))

            Spacer()

            Div({ classes(AppStyles.group, AppStyles.serviceColumns) }) {
                ServiceColumn(
                    title = "Android",
                    icon = "/android.svg",
                    description = tr(
                        "Nativní aplikace na míru – od návrhu až po vydání na Google Play.",
                        "Custom native apps – from design all the way to Google Play.",
                    ),
                    bullets = listOf(
                        tr("návrh UX/UI a prototyp", "UX/UI design & prototype"),
                        tr("převzetí a modernizace stávající aplikace", "taking over & modernising an existing app"),
                        tr("aplikace pro hodinky Wear OS", "Wear OS watch apps"),
                    ),
                )
                ServiceColumn(
                    title = "Kotlin",
                    icon = "/kotlin.svg",
                    description = tr(
                        "Backend, sdílený kód pro Android i iOS a weby jako tento.",
                        "Backend, shared code for Android & iOS and websites like this one.",
                    ),
                    bullets = listOf(
                        tr("API a servery v Ktoru", "APIs & servers in Ktor"),
                        tr("Multiplatform – jedna logika pro Android i iOS", "Multiplatform – one logic for Android & iOS"),
                        tr("webové prezentace", "websites & landing pages"),
                    ),
                )
                ServiceColumn(
                    title = "AI",
                    icon = "/ai.svg",
                    description = tr(
                        "AI, která šetří čas – ve vaší aplikaci i ve firemních procesech.",
                        "AI that saves time – in your app and in your company processes.",
                    ),
                    bullets = listOf(
                        tr("chatboti a asistenti nad vašimi daty", "chatbots & assistants over your own data"),
                        tr("automatizace rutinní práce", "automating routine work"),
                        tr("napojení na Claude API a MCP", "Claude API & MCP integration"),
                    ),
                )
            }

            Spacer()

            Div({ classes(AppStyles.fillWidth) }) {
                A(href = "#svatebka", attrs = { attr("aria-label", tr("Posunout dolů", "Scroll down")) }) {
                    Icon(src = "/arrow-double-down.svg", bounce = true)
                }
            }
        }
    }
}

@Composable
private fun ServiceColumn(title: String, icon: String, description: String, bullets: List<String>) {
    Div({ classes(AppStyles.serviceColumn) }) {
        Subtitle(title) { Icon(src = icon, alt = title) }
        Body(description, small = true)
        Div {
            bullets.forEach { Bullet(it, Colors.primaryText, small = true) }
        }
    }
}
