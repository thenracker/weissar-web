package weissar.web.concept

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Main
import weissar.web.components.ContactModal
import weissar.web.concept.components.Marquee
import weissar.web.concept.data.technologies
import weissar.web.concept.sections.ApproachSection
import weissar.web.concept.sections.ContactSection
import weissar.web.concept.sections.EducationSection
import weissar.web.concept.sections.IntroSection
import weissar.web.concept.sections.Header
import weissar.web.concept.sections.ExperienceSection
import weissar.web.concept.sections.ExpertiseSection
import weissar.web.concept.sections.ProductsSection
import weissar.web.concept.theme.AppStyles
import weissar.web.theme.AppStyles as LegacyStyles

/** New design on weissar.dev/concept, one long page instead of full-screen sections. */
@Composable
fun Page() {
    // AppStyles for the shared contact modal
    Style(LegacyStyles)
    Style(AppStyles)

    var contactOpen by remember { mutableStateOf(false) }
    val openContact = { contactOpen = true }

    Div({ classes(AppStyles.page) }) {
        Header(openContact)
        Main {
            IntroSection(openContact)
            Marquee(technologies)
            Div({ style { property("height", "clamp(24px, 3vw, 40px)") } })
            ApproachSection()
            ProductsSection()
            ExpertiseSection()
            Marquee(technologies)
            ExperienceSection()
            EducationSection()
            ContactSection(openContact)
        }

        if (contactOpen) ContactModal(onClose = { contactOpen = false })
    }
}
