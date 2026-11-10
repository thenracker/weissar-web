package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import weissar.web.concept.components.Columns
import weissar.web.concept.components.Observed
import weissar.web.concept.components.SectionHeading
import weissar.web.concept.data.expertise
import weissar.web.concept.theme.AppStyles
import weissar.web.i18n.tr

@Composable
fun ExpertiseSection() {
    Observed(id = "expertiza", className = AppStyles.block) {
        Div({ classes(AppStyles.container) }) {
            SectionHeading(
                number = "03",
                eyebrow = tr("Expertíza", "Expertise"),
                first = tr("Od architektury", "From architecture"),
                second = tr("po pocit z produktu.", "to how the product feels."),
                note = tr(
                    "Technologie vybírám podle problému. Těžiště zůstává v nativním Androidu a Kotlinu, se zkušeností napříč backendem, multiplatformou i produktovým designem.",
                    "I pick technologies to fit the problem. The core stays in native Android and Kotlin, with experience across backend, multiplatform and product design.",
                ),
            )
            Columns(expertise)
        }
    }
}
