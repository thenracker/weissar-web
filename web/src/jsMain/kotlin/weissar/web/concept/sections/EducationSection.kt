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
            Reveal(0) { Eyebrow("05", tr("Vzdělání", "Education")) }
            Reveal(1) {
                DisplayTitle(
                    tr("Technický základ.", "Technical foundation."),
                    tr("Mezinárodní přesah.", "International reach."),
                    accentFirst = true,
                )
            }
            Reveal(2) {
                P({ classes(AppStyles.lead) }) {
                    Text(
                        tr(
                            "Aplikovaná informatika, datová věda a zkušenosti z vývoje i studia v Asii.",
                            "Applied informatics, data science and experience from development and studies in Asia.",
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
