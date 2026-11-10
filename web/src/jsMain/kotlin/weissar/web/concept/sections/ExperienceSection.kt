package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import weissar.web.concept.components.Columns
import weissar.web.concept.components.Observed
import weissar.web.concept.components.SectionHeading
import weissar.web.concept.data.experience
import weissar.web.concept.theme.AppStyles
import weissar.web.i18n.tr

@Composable
fun ExperienceSection() {
    Observed(id = "zkusenosti", className = AppStyles.block) {
        Div({ classes(AppStyles.container) }) {
            SectionHeading(
                number = "04",
                eyebrow = tr("Zkušenosti", "Experience"),
                first = tr("Kontexty se mění.", "Contexts change."),
                second = tr("Nároky na kvalitu ne", "The bar for quality doesn't"),
                accentFirst = true,
                note = tr(
                    "Bankovnictví, retail, výzkum i vlastní produkty. Vybrané spolupráce od prvních Java aplikací po vedení Android vývoje.",
                    "Banking, retail, research and my own products. Selected work from the first Java apps to leading Android development.",
                ),
            )
            Columns(experience)
        }
    }
}
