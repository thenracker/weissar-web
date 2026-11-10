package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
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
                    H2({ classes(AppStyles.display) }) { Text(tr("Máte složitý produktový problém?", "Got a tricky product problem?")) }
                }
                Reveal(1) {
                    Div({ classes(AppStyles.actions) }) {
                        Button({
                            classes(AppStyles.button)
                            onClick { onContact() }
                        }) {
                            Text(tr("Pojďme ho rozmotat", "Let's untangle it"))
                            MaterialIcon("arrow_forward", AppStyles.buttonIcon)
                        }
                    }
                }
            }
            Div({
                classes(AppStyles.watermark)
                attr("aria-hidden", "true")
            })
        }
        Div({ classes(AppStyles.footer) }) {
            A(href = "mailto:weissar.petr@gmail.com", attrs = { classes(AppStyles.footerLink) }) { Text("weissar.petr@gmail.com") }
            A(href = "tel:+420725791445", attrs = { classes(AppStyles.footerLink) }) { Text("+420 725 791 445") }
            Span({ classes(AppStyles.footerLink) }) { Text("Pardubice · Hradec Králové · Praha") }
        }
    }
}
