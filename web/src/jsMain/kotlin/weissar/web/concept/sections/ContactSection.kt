package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.components.AnimatedLogo
import weissar.web.concept.components.MaterialIcon
import weissar.web.concept.components.Observed
import weissar.web.concept.components.Reveal
import weissar.web.concept.theme.AppStyles
import weissar.web.i18n.tr

@Composable
fun ContactSection(onContact: () -> Unit) {
    Observed(id = "kontakt", className = AppStyles.container) {
        Div({ classes(AppStyles.contact) }) {
            Div {
                Reveal(0) {
                    H2({ classes(AppStyles.display) }) { Text(tr("S čím vám mohu pomoci?", "How can I help you?")) }
                }
                Reveal(1) {
                    Div({ classes(AppStyles.actions) }) {
                        Button({
                            classes(AppStyles.button)
                            onClick { onContact() }
                        }) {
                            Text(tr("Napište mi", "Get in touch"))
                            MaterialIcon("arrow_forward", AppStyles.buttonIcon)
                        }
                        A(href = "https://www.linkedin.com/in/weissar-petr/", attrs = {
                            classes(AppStyles.textButton)
                            attr("target", "_blank")
                            attr("rel", "noopener")
                        }) {
                            Text("LinkedIn")
                            MaterialIcon("arrow_forward", AppStyles.buttonIcon)
                        }
                    }
                }
            }
            Div({
                classes(AppStyles.watermark)
                attr("aria-hidden", "true")
            }) {
                AnimatedLogo(color = "currentColor", loop = true)
            }
        }
        Div({ classes(AppStyles.footer) }) {
            Span { Text("Pardubice · Hradec Králové · Praha") }
            Span { Text("Made with love in Compose Web") }
        }
    }
}
