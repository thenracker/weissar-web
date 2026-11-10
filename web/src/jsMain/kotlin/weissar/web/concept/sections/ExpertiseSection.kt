package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import weissar.web.concept.components.Columns
import weissar.web.concept.components.Observed
import weissar.web.concept.components.SectionHeading
import weissar.web.concept.data.expertise
import weissar.web.concept.theme.AppStyles
import weissar.web.i18n.tr

@Composable
fun ExpertiseSection() {
    Observed(id = "expertiza", className = AppStyles.block) {
        Div({ classes(AppStyles.container) }) {
            SectionHeading(
                number = "03",
                eyebrow = tr("Co dělám", "What I do"),
                first = tr("Android, backend", "Android, backend"),
                second = tr("a UX/UI konzultace.", "and UX/UI consulting."),
                note = tr(
                    "Hlavně nativní Android v Kotlinu. K tomu backend, sdílený kód pro iOS a UX/UI konzultace – ať stavíte novou aplikaci, nebo potřebujete dát do pořádku tu stávající.",
                    "Mostly native Android in Kotlin. Plus backend, shared code for iOS and UX/UI consulting – whether you're building a new app or need to fix up the one you have.",
                ),
            )
            Columns(expertise)
        }
    }
}
