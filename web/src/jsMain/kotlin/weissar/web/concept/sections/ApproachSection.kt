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
            Reveal(0) { Eyebrow("01", tr("Jak pracuji", "How I work")) }
            Reveal(1) {
                DisplayTitle(
                    tr("Vývojář, analytik", "Developer, analyst"),
                    tr("i konzultant v jednom.", "and consultant, all in one."),
                )
            }
            Reveal(2) {
                P({ classes(AppStyles.lead) }) {
                    Text(
                        tr(
                            "Na projektech většinou nemám jen jednu roli. Projdu s vámi zadání, navrhnu obrazovky, napíšu aplikaci i server, se kterým komunikuje – a když je potřeba, převezmu i projekt, který rozjel někdo jiný.",
                            "On most projects I don't stick to a single role. I go through the brief with you, design the screens, write the app and the server it talks to – and if needed, take over a project someone else started.",
                        ),
                    )
                }
            }
        }
    }
}
