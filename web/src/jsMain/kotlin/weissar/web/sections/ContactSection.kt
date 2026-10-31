package weissar.web.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.components.Body
import weissar.web.components.Content
import weissar.web.components.Logo
import weissar.web.components.Section
import weissar.web.components.Spacer
import weissar.web.components.Title
import weissar.web.i18n.tr
import weissar.web.theme.AppStyles
import weissar.web.theme.Colors

/** Last slide – footer with contacts. */
@Composable
fun ContactSection() {
    val textColor = Colors.white

    Section(
        id = "contact",
        background = Colors.backgroundBlack,
        left = {
            Content {
                Title(tr("Kontakt", "Contact"), textColor)
                Body(tr("Napiš, zavolej, nebo zajdem na kafe.", "Drop me a line, call me, or let's grab a coffee."), Colors.secondaryTextNight)

                Spacer()

                Div({ classes(AppStyles.group, AppStyles.contactList) }) {
                    ContactRow("contact-linkedin.svg", "LinkedIn", href = "https://www.linkedin.com/in/weissar-petr/")
                    ContactRow("contact-instagram.svg", "Instagram", href = "https://www.instagram.com/petr.weissar/")
                }

                Spacer()

                Div({ classes(AppStyles.group, AppStyles.contactList) }) {
                    ContactRow("contact-remote.svg", tr("Pracuju remote", "Working remotely"))
                    ContactRow("contact-location.svg", "Pardubice · Hradec Králové · Praha")
                }
            }
        },
        right = {
            Content {
                Logo(src = "pw-logo-white.svg", alt = "PW", alignEnd = true)
                Spacer()
                Div({ classes(AppStyles.end) }) {
                    Body("© 2026 Petr Weissar", Colors.secondaryTextNight, small = true)
                }
            }
        },
    )
}

/** Icon in a round bubble followed by a text, clickable when [href] is set. */
@Composable
private fun ContactRow(icon: String, text: String, href: String? = null) {
    val content: @Composable () -> Unit = {
        Span({ classes(AppStyles.contactIcon) }) { Img(src = icon, alt = "") }
        Span({ classes(AppStyles.body) }) { Text(text) }
    }

    if (href == null) {
        Div({ classes(AppStyles.contactRow) }) { content() }
    } else {
        A(href = href, attrs = {
            classes(AppStyles.contactRow, AppStyles.contactLink)
            if (href.startsWith("http")) {
                attr("target", "_blank")
                attr("rel", "noopener")
            }
        }) { content() }
    }
}
