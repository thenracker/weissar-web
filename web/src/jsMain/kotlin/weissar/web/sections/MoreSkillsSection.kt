package weissar.web.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.components.Content
import weissar.web.components.FullSection
import weissar.web.components.Spacer
import weissar.web.components.Title
import weissar.web.data.skillGroups
import weissar.web.i18n.tr
import weissar.web.theme.AppStyles
import weissar.web.theme.Colors

/** Continuation of the skills – everything else as chips. */
@Composable
fun MoreSkillsSection() {
    FullSection(id = "more", background = Colors.backgroundDark) {
        Content {
            Title(tr("A další…", "And more…"), Colors.primaryTextNight)

            Spacer()

            Div({ classes(AppStyles.group, AppStyles.chipGroups) }) {
                skillGroups.forEach { group ->
                    Div {
                        H3({ classes(AppStyles.chipLabel) }) { Text(group.label.text) }
                        Div({ classes(AppStyles.chips) }) {
                            group.skills.forEach { skill ->
                                Span({
                                    classes(AppStyles.chip)
                                    if (group.featured) classes(AppStyles.chipFeatured)
                                }) { Text(skill.text) }
                            }
                        }
                    }
                }
            }

            Spacer()
        }
    }
}
