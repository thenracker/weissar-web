package weissar.web.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import weissar.web.components.AnimatedLogo
import weissar.web.components.Body
import weissar.web.components.Content
import weissar.web.components.Group
import weissar.web.components.Icon
import weissar.web.components.Overline
import weissar.web.components.Section
import weissar.web.components.Spacer
import weissar.web.components.Title
import weissar.web.components.Typewriter
import weissar.web.i18n.tr
import weissar.web.theme.AppStyles

@Composable
fun IntroSection() {
    Section(
        id = "intro",
        overlayOnMobile = true,
        left = {
            Content {
                AnimatedLogo()

                Spacer()

                Group {
                    Overline("Ing. et Ing.")
                    Title("Petr Weissar")
                }

                Spacer()

                Group {
                    // Starts typing once the name and the photo are in
                    Typewriter(
                        lines = listOf(
                            tr("Senior Android vývojář", "Senior Android developer"),
                            tr("Softwarový analytik", "Software analyst"),
                            tr("UX/UI konzultant", "UX/UI consultant"),
                        ),
                        startDelayMs = 1700,
                        finale = tr("S čím vám mohu pomoci?", "How can I help you?"),
                        finalePauseMs = 3000,
                    )

                    Div({ classes(AppStyles.fillWidth, AppStyles.introActions) }) {
                        WriteMeButton(AppStyles.onlyMobile)
                        A(href = "#services", attrs = { attr("aria-label", tr("Posunout dolů", "Scroll down")) }) {
                            Icon(src = "arrow-double-down.svg", bounce = true)
                        }
                    }
                }
            }
        },
        right = {
            var photoLoaded by remember { mutableStateOf(false) }

            WriteMeButton(AppStyles.photoButton, AppStyles.hideOnMobile)

            Div({ classes(AppStyles.photoFrame) }) {
                Img(src = "weissar.webp", alt = "Petr Weissar") {
                    classes(AppStyles.fullImage)
                    if (photoLoaded) classes(AppStyles.loaded)
                    addEventListener("load") { photoLoaded = true }
                    // Cached image may be loaded before onLoad is attached
                    ref { image ->
                        if (image.complete) photoLoaded = true
                        onDispose { }
                    }
                }
            }
        },
    )
}

/** Shortcut to the contacts slide. */
@Composable
private fun WriteMeButton(vararg extraClasses: String) {
    A(href = "#contact", attrs = { classes(AppStyles.button, *extraClasses) }) {
        Body(tr("Napište mi", "Get in touch"))
        Icon(src = "message.svg")
    }
}
