package weissar.web.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import weissar.web.data.Project
import weissar.web.i18n.tr
import weissar.web.theme.AppStyles

/** Detail of a project over the page, closes on the backdrop, the cross or Escape. */
@Composable
fun ProjectModal(project: Project, onClose: () -> Unit) {
    DisposableEffect(Unit) {
        val onKey: (Event) -> Unit = { if ((it as KeyboardEvent).key == "Escape") onClose() }
        window.addEventListener("keydown", onKey)
        // Page under the modal must not scroll
        document.documentElement?.classList?.add(AppStyles.noScroll)
        onDispose {
            window.removeEventListener("keydown", onKey)
            document.documentElement?.classList?.remove(AppStyles.noScroll)
        }
    }

    Div({
        classes(AppStyles.modalBackdrop)
        onClick { onClose() }
    }) {
        Div({
            classes(AppStyles.modal)
            attr("role", "dialog")
            attr("aria-modal", "true")
            attr("aria-label", project.company)
            onClick { it.stopPropagation() }
        }) {
            Button({
                classes(AppStyles.modalClose)
                attr("aria-label", tr("Zavřít", "Close"))
                onClick { onClose() }
            }) { Text("✕") }

            Div({ classes(AppStyles.modalHeader) }) {
                CompanyLogo(project, large = true)
                Div {
                    H3({ classes(AppStyles.modalTitle) }) { Text(project.company) }
                    P({ classes(AppStyles.cardMeta) }) {
                        Text(listOfNotNull(project.role.text, project.years.text, project.location?.text).joinToString(" · "))
                    }
                }
            }

            P({ classes(AppStyles.modalText) }) { Text(project.detail.text) }

            if (project.highlights.isNotEmpty()) {
                H4({ classes(AppStyles.modalHeading) }) { Text(tr("Na čem jsem dělal", "What I worked on")) }
                Ul({ classes(AppStyles.modalList) }) {
                    project.highlights.forEach { Li { Text(it.text) } }
                }
            }

            if (project.timeline.isNotEmpty()) {
                H4({ classes(AppStyles.modalHeading) }) { Text(tr("Časová osa", "Timeline")) }
                Div({ classes(AppStyles.timeline) }) {
                    project.timeline.forEach { milestone ->
                        Div({ classes(AppStyles.timelineItem) }) {
                            Span({ classes(AppStyles.timelineDate) }) { Text(milestone.date.text) }
                            Span({ classes(AppStyles.timelineText) }) { Text(milestone.text.text) }
                        }
                    }
                }
            }

            Tags(project.tags)

            if (project.link != null) {
                A(href = project.link, attrs = {
                    classes(AppStyles.button, AppStyles.modalLink)
                    attr("target", "_blank")
                    attr("rel", "noopener")
                }) {
                    Span({ classes(AppStyles.cardText) }) { Text(tr("Více informací", "Learn more")) }
                    Icon(src = "arrow-more.svg")
                }
            }
        }
    }
}
