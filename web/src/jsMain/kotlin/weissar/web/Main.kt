package weissar.web

import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable
import weissar.web.components.LanguageSwitch
import weissar.web.components.SectionDots
import weissar.web.sections.ContactSection
import weissar.web.sections.EducationSection
import weissar.web.sections.IntroSection
import weissar.web.sections.MoreSkillsSection
import weissar.web.sections.ProjectsSection
import weissar.web.sections.ServicesSection
import weissar.web.sections.SkillsSection
import weissar.web.sections.SvatebkaSection
import weissar.web.sections.UniverzitaSection
import weissar.web.theme.AppStyles

fun main() {
    renderComposable(rootElementId = "root") {
        Style(AppStyles)

        IntroSection()
        ServicesSection()
        SvatebkaSection()
        UniverzitaSection()
        ProjectsSection()
        ContactSection()
        SkillsSection()
        MoreSkillsSection()
        EducationSection()

        val lightSectionIds = setOf("services", "projects", "education")
        SectionDots(
            sectionIds = listOf("intro", "services", "svatebka", "univerzita", "projects", "contact", "skills", "more", "education"),
            lightSectionIds = lightSectionIds,
        )
        LanguageSwitch(lightSectionIds)
    }
}
