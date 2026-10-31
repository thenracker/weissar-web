package weissar.web.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.dom.Div
import weissar.web.components.Content
import weissar.web.components.FullSection
import weissar.web.components.ProjectCard
import weissar.web.components.ProjectModal
import weissar.web.data.Project
import weissar.web.components.Spacer
import weissar.web.components.Title
import weissar.web.data.projects
import weissar.web.i18n.tr
import weissar.web.theme.AppStyles
import weissar.web.theme.Colors

@Composable
fun ProjectsSection() {
    var selected by remember { mutableStateOf<Project?>(null) }

    FullSection(id = "projects", background = Colors.backgroundLight) {
        Content {
            Title(tr("Spolupracoval jsem na", "Collaborated on"))

            Spacer()

            Div({ classes(AppStyles.group, AppStyles.cardGrid) }) {
                projects.forEach { project -> ProjectCard(project, onClick = { selected = project }) }
            }

            Spacer()
        }

        selected?.let { project -> ProjectModal(project, onClose = { selected = null }) }
    }
}
