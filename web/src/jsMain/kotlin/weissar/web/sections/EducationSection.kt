package weissar.web.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.components.Content
import weissar.web.components.FullSection
import weissar.web.components.ProjectCard
import weissar.web.components.ProjectModal
import weissar.web.components.Spacer
import weissar.web.components.Title
import weissar.web.data.Project
import weissar.web.data.awards
import weissar.web.data.certificates
import weissar.web.data.education
import weissar.web.i18n.Translated
import weissar.web.i18n.tr
import weissar.web.theme.AppStyles
import weissar.web.theme.Colors

@Composable
fun EducationSection() {
    var selected by remember { mutableStateOf<Project?>(null) }

    FullSection(id = "education", background = Colors.white) {
        Content {
            Title(tr("Vzdělání", "Education"))

            Spacer()

            Div({ classes(AppStyles.group, AppStyles.cardGrid, AppStyles.cardGridMuted) }) {
                education.forEach { item -> ProjectCard(item, onClick = { selected = item }) }
            }

            Spacer()

            Div({ classes(AppStyles.group, AppStyles.chipGroups) }) {
                ChipList(tr("Ocenění", "Awards"), awards)
                ChipList(tr("Certifikáty a další", "Certificates & more"), certificates)
            }

            Spacer()

            Div({ classes(AppStyles.footer) }) {
                P({ classes(AppStyles.footnote) }) { Text("© 2026 Petr Weissar") }
                P({ classes(AppStyles.footnote) }) { Text("Made with love in Compose Web") }
            }
        }

        selected?.let { item -> ProjectModal(item, onClose = { selected = null }) }
    }
}

@Composable
private fun ChipList(label: String, items: List<Translated>) {
    Div {
        H3({ classes(AppStyles.chipLabel, AppStyles.chipLabelLight) }) { Text(label) }
        Div({ classes(AppStyles.chips) }) {
            items.forEach { Span({ classes(AppStyles.chip, AppStyles.chipLight) }) { Text(it.text) } }
        }
    }
}
