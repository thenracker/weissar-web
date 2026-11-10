package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import weissar.web.concept.components.DisplayTitle
import weissar.web.concept.components.Eyebrow
import weissar.web.concept.components.Observed
import weissar.web.concept.components.Reveal
import weissar.web.concept.theme.AppStyles
import weissar.web.i18n.tr

@Composable
fun ApproachSection() {
    Observed(id = "pristup", className = AppStyles.card) {
        Div({ classes(AppStyles.cardContent) }) {
            Reveal(0) { Eyebrow("01", tr("Můj přístup", "My approach")) }
            Reveal(1) {
                DisplayTitle(
                    tr("Nepíšu jenom kód,", "I don't just write code,"),
                    tr("hledám ten správný problém", "I look for the right problem"),
                )
            }
            Reveal(2) {
                P({ classes(AppStyles.lead) }) {
                    Text(
                        tr(
                            "Do každého projektu vždy vstupuju jako vývojář, analytik i konzultant. Pomáhám přenést nejasný záměr do srozumitelného produktu. A to od architektury, datových toků až po poslední detail v uživatelském prostředí.",
                            "I join every project as a developer, analyst and consultant. I help turn a vague intention into a clear product – from architecture and data flows to the last detail of the user interface.",
                        ),
                    )
                }
            }
        }
    }
}
