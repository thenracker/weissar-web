package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text
import weissar.web.components.AnimatedLogo
import weissar.web.components.LanguageSwitch
import weissar.web.concept.components.MaterialIcon
import weissar.web.concept.theme.Colors
import weissar.web.concept.theme.AppStyles
import weissar.web.i18n.tr
import weissar.web.theme.AppStyles as LegacyStyles

@Composable
fun Header(onContact: () -> Unit) {
    // Always visible, so the logo draws itself right away and the rest waits for it
    Nav({ classes(AppStyles.nav, LegacyStyles.visible, AppStyles.afterLogo) }) {
        A(href = "/concept/", attrs = {
            classes(AppStyles.navLogo)
            attr("aria-label", "Petr Weissar")
        }) {
            AnimatedLogo(color = Colors.text.toString())
        }
        Div({
            classes(AppStyles.navLinks, AppStyles.reveal)
            style { property("--d", "0") }
        }) {
            A(href = "#projekty", attrs = { classes(AppStyles.navLink) }) { Text(tr("Projekty", "Projects")) }
            A(href = "#expertiza", attrs = { classes(AppStyles.navLink) }) { Text(tr("Co dělám", "What I do")) }
            A(href = "#zkusenosti", attrs = { classes(AppStyles.navLink) }) { Text(tr("Zkušenosti", "Experience")) }
        }
        Div({
            classes(AppStyles.navEnd, AppStyles.reveal)
            style { property("--d", "1") }
        }) {
            // flag-icons (MIT), grayscale in the stylesheet
            LanguageSwitch(csFlag = "/concept/flags/cz.svg", enFlag = "/concept/flags/gb.svg")
            Button({
                classes(AppStyles.button)
                onClick { onContact() }
            }) {
                Text(tr("Napište mi", "Get in touch"))
                MaterialIcon("arrow_forward", AppStyles.buttonIcon)
            }
        }
    }
}
