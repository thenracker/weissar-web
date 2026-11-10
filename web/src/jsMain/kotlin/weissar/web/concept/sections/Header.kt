package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text
import weissar.web.concept.components.MaterialIcon
import weissar.web.concept.theme.AppStyles
import weissar.web.i18n.tr

@Composable
fun Header(onContact: () -> Unit) {
    Nav({ classes(AppStyles.nav) }) {
        A(href = "/concept/") {
            Img(src = "/pw-logo-white.svg", alt = "Petr Weissar") { classes(AppStyles.navLogo) }
        }
        Div({ classes(AppStyles.navLinks) }) {
            A(href = "#projekty", attrs = { classes(AppStyles.navLink) }) { Text(tr("Projekty", "Projects")) }
            A(href = "#expertiza", attrs = { classes(AppStyles.navLink) }) { Text(tr("Expertíza", "Expertise")) }
            A(href = "#zkusenosti", attrs = { classes(AppStyles.navLink) }) { Text(tr("Zkušenosti", "Experience")) }
        }
        Button({
            classes(AppStyles.button, AppStyles.navCta)
            onClick { onContact() }
        }) {
            Text(tr("Napište mi", "Get in touch"))
            MaterialIcon("arrow_forward", AppStyles.buttonIcon)
        }
    }
}
