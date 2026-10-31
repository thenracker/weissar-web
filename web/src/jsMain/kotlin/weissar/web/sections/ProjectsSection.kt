package weissar.web.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import weissar.web.components.Content
import weissar.web.components.FullSection
import weissar.web.components.ProjectCard
import weissar.web.components.Spacer
import weissar.web.components.Title
import weissar.web.data.projects
import weissar.web.i18n.tr
import weissar.web.theme.AppStyles
import weissar.web.theme.Colors

@Composable
fun ProjectsSection() {
    FullSection(id = "projects", background = Colors.backgroundLight) {
        Content {
            Title(tr("Spolupracoval jsem na", "Collaborated on"))

            Spacer()

            Div({ classes(AppStyles.group, AppStyles.cardGrid) }) {
                projects.forEach { ProjectCard(it) }
            }

            Spacer()
        }
    }
}
