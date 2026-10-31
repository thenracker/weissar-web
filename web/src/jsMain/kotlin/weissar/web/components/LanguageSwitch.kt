package weissar.web.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import weissar.web.i18n.Language
import weissar.web.i18n.Localization
import weissar.web.scroll.SectionObserver
import weissar.web.theme.AppStyles

/**
 * White outlined flags in the top right corner.
 * Flags turn dark on sections from [lightSectionIds].
 */
@Composable
fun LanguageSwitch(lightSectionIds: Set<String> = emptySet()) {
    Div({
        classes(AppStyles.languageSwitch)
        if (SectionObserver.activeSection in lightSectionIds) classes(AppStyles.languageSwitchDark)
    }) {
        Flag(Language.CS, src = "flag-cs.svg", label = "Čeština")
        Flag(Language.EN, src = "flag-en.svg", label = "English")
    }
}

@Composable
private fun Flag(language: Language, src: String, label: String) {
    val selected = Localization.language == language

    Button({
        classes(AppStyles.flag)
        if (selected) classes(AppStyles.flagSelected)
        attr("aria-label", label)
        attr("aria-pressed", selected.toString())
        onClick { Localization.select(language) }
    }) {
        Img(src = src, alt = "")
    }
}
