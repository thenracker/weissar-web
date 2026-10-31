package weissar.web.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.data.Project
import weissar.web.theme.AppStyles

@Composable
fun ProjectCard(project: Project) {
    Div({ classes(AppStyles.card) }) {
        Div({ classes(AppStyles.cardHeader) }) {
            Div({
                classes(AppStyles.companyLogo)
                if (project.logoFills) classes(AppStyles.companyLogoFill)
            }) {
                if (project.logo != null) {
                    Img(src = "companies/${project.logo}", alt = project.company)
                } else {
                    Img(src = "companies/placeholder.svg", alt = "")
                }
            }
            P({ classes(AppStyles.cardMeta) }) { Text(project.years.text) }
        }
        H3({ classes(AppStyles.cardTitle) }) { Text(project.company) }
        P({ classes(AppStyles.cardText) }) { Text(project.description.text) }
        P({ classes(AppStyles.cardMeta) }) { Text(project.role.text) }

        Div({ classes(AppStyles.tags) }) {
            project.tags.forEach { tag ->
                Span({ classes(AppStyles.tag) }) { Text(tag) }
            }
        }
    }
}
