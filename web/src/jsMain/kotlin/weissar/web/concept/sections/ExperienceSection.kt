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
                first = tr("Banka, retail", "Banking, retail"),
                second = tr("i agenturní zakázky.", "and agency work."),
                accentFirst = true,
                note = tr(
                    "V IT od roku 2011 – od desktopových aplikací v Javě přes mobilní agenturu a vedení Android týmu až po mobilní bankovnictví.",
                    "In IT since 2011 – from desktop apps in Java through a mobile agency and leading an Android team to mobile banking.",
                ),
            )
            Columns(experience)
        }
    }
}
