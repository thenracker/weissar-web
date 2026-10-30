package weissar.web

import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable
import weissar.web.sections.IntroSection
import weissar.web.sections.SkillsSection
import weissar.web.sections.SvatebkaSection
import weissar.web.theme.AppStyles

fun main() {
    renderComposable(rootElementId = "root") {
        Style(AppStyles)

        IntroSection()
        SkillsSection()
        SvatebkaSection()
    }
}
