package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.concept.components.DisplayTitle
import weissar.web.concept.components.Eyebrow
import weissar.web.concept.components.MaterialIcon
import weissar.web.concept.components.Observed
import weissar.web.concept.components.Reveal
import weissar.web.concept.data.educationRows
import weissar.web.concept.data.experience
import weissar.web.concept.theme.AppStyles
import weissar.web.i18n.tr

@Composable
fun EducationSection() {
    Observed(id = "vzdelani", className = AppStyles.card) {
        Div({ classes(AppStyles.cardContent) }) {
            Reveal(0) { Eyebrow("05", tr("Vzdělávání a mezinárodní spolupráce", "Education & international cooperation")) }
            Reveal(1) {
                DisplayTitle(
                    tr("Dva inženýrské tituly", "Two engineering degrees"),
                    tr("a stáže od Floridy po Asii.", "and internships from Florida to Asia."),
                    accentFirst = true,
                )
            }
            Reveal(2) {
                P({ classes(AppStyles.lead) }) {
                    Text(
                        tr(
                            "Aplikovaná informatika a datová věda na Univerzitě Hradec Králové, k tomu vývoj pro Android na Floridě a stáže a letní školy v Hongkongu, na Tchaj-wanu a v Číně.",
                            "Applied informatics and data science at the University of Hradec Králové, plus Android development in Florida and internships and summer schools in Hong Kong, Taiwan and China.",
                        ),
                    )
                }
            }
            Reveal(3, AppStyles.table) {
                educationRows.forEach { row ->
                    Div({ classes(AppStyles.row) }) {
                        Span({ classes(AppStyles.rowYears) }) {
                            MaterialIcon("subdirectory_arrow_right", AppStyles.arrowIcon)
                            Text(row.years)
                        }
                        Span { Text(row.school) }
                        Span { Text(row.detail.text) }
                    }
                }
            }
        }
    }
}
