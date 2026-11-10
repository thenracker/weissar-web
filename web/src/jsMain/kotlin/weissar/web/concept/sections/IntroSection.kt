package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.concept.components.MaterialIcon
import weissar.web.concept.components.MorphingPhoto
import weissar.web.concept.components.Observed
import weissar.web.concept.components.Reveal
import weissar.web.concept.components.TypedTitles
import weissar.web.concept.theme.AppStyles
import weissar.web.i18n.tr

@Composable
fun IntroSection(onContact: () -> Unit) {
    Observed(id = "intro", className = AppStyles.container) {
        Div({ classes(AppStyles.intro, AppStyles.afterLogo) }) {
            Div({ classes(AppStyles.introText) }) {
                Reveal(0) {
                    P({ classes(AppStyles.badge) }) {
                        Span({ classes(AppStyles.dot, AppStyles.pulse) })
                        TypedTitles(
                            titles = listOf(
                                tr("Senior Android vývojář", "Senior Android developer"),
                                tr("Softwarový analytik", "Software analyst"),
                                tr("UX/UI konzultant", "UX/UI consultant"),
                            ),
                            // After the logo and the intro reveal
                            startDelayMs = 1600,
                        )
                    }
                }
                Reveal(1) {
                    H1({ classes(AppStyles.display) }) {
                        Span({ classes(AppStyles.line) }) { Text(tr("Navrhnu, naprogramuji", "I design, build")) }
                        Text(" ")
                        Span({ classes(AppStyles.accent) }) { Text(tr("a vydám vaši aplikaci.", "and ship your app.")) }
                    }
                }
                Reveal(2) {
                    P({ classes(AppStyles.lead) }) {
                        Text(
                            tr(
                                "Jsem Petr, Android vývojář od roku 2015. Kromě aplikací píšu i backend v Kotlinu a pomáhám s UX/UI – od prvního nápadu až po vydání na Google Play.",
                                "I'm Petr, an Android developer since 2015. Besides apps I write Kotlin backends and help with UX/UI – from the first idea all the way to Google Play.",
                            ),
                        )
                    }
                }
                Reveal(3) {
                    Div({ classes(AppStyles.actions) }) {
                        A(href = "#projekty", attrs = { classes(AppStyles.button) }) {
                            Text(tr("Prohlédnout projekty", "See the projects"))
                        }
                        Button({
                            classes(AppStyles.textButton)
                            onClick { onContact() }
                        }) {
                            Text(tr("Napište mi", "Get in touch"))
                            MaterialIcon("arrow_forward", AppStyles.buttonIcon)
                        }
                    }
                }
            }

            Reveal(2, AppStyles.visual) { IntroVisual() }
        }
    }
}

/** Photo morphing through Material 3 Expressive shapes. */
@Composable
private fun IntroVisual() {
    Div({ classes(AppStyles.frame) }) {
        MorphingPhoto(src = "/weissar.webp", alt = "Petr Weissar")
    }
}
