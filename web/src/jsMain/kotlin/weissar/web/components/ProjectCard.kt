package weissar.web.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.data.Project
import weissar.web.i18n.tr
import weissar.web.theme.AppStyles

@Composable
fun ProjectCard(project: Project, onClick: () -> Unit) {
    Div({
        classes(AppStyles.card)
        attr("role", "button")
        attr("tabindex", "0")
        attr("aria-label", tr("Detail – ${project.company}", "Detail – ${project.company}"))
        onClick { onClick() }
        onKeyDown { event ->
            if (event.key == "Enter" || event.key == " ") {
                event.preventDefault()
                onClick()
            }
        }
    }) {
        Div({ classes(AppStyles.cardHeader) }) {
            CompanyLogo(project)
            P({ classes(AppStyles.cardMeta) }) { Text(project.years.text) }
        }
        H3({ classes(AppStyles.cardTitle) }) { Text(project.company) }
        P({ classes(AppStyles.cardText) }) { Text(project.description.text) }
        P({ classes(AppStyles.cardMeta) }) { Text(project.role.text) }

        Tags(project.tags)
    }
}

/** Company logo in a rounded badge, placeholder icon when there's none. */
@Composable
fun CompanyLogo(project: Project, large: Boolean = false) {
    Div({
        classes(AppStyles.companyLogo)
        if (project.logoFills) classes(AppStyles.companyLogoFill)
        if (large) classes(AppStyles.companyLogoLarge)
    }) {
        if (project.logo != null) {
            Img(src = "/companies/${project.logo}", alt = project.company)
        } else {
            Img(src = "/companies/placeholder.svg", alt = "")
        }
    }
}

@Composable
fun Tags(tags: List<String>) {
    Div({ classes(AppStyles.tags) }) {
        tags.forEach { tag ->
            Span({ classes(AppStyles.tag) }) { Text(tag) }
        }
    }
}
