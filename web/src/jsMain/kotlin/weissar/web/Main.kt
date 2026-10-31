package weissar.web

import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable
import weissar.web.components.LanguageSwitch
import weissar.web.components.SectionDots
import weissar.web.sections.IntroSection
import weissar.web.sections.ProjectsSection
import weissar.web.sections.SkillsSection
import weissar.web.sections.SvatebkaSection
import weissar.web.sections.UniverzitaSection
import weissar.web.theme.AppStyles

fun main() {
    renderComposable(rootElementId = "root") {
        Style(AppStyles)

        IntroSection()
        SkillsSection()
        SvatebkaSection()
        UniverzitaSection()
        ProjectsSection()

        SectionDots(
            sectionIds = listOf("intro", "skills", "svatebka", "univerzita", "projects"),
            lightSectionIds = setOf("projects"),
        )
        LanguageSwitch(lightSectionIds = setOf("projects"))
    }
}
