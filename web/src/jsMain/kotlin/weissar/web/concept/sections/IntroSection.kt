package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.concept.components.MaterialIcon
import weissar.web.concept.components.MorphingPhoto
import weissar.web.concept.components.Observed
import weissar.web.concept.components.Reveal
import weissar.web.concept.theme.AppStyles
import weissar.web.i18n.tr

@Composable
fun IntroSection(onContact: () -> Unit) {
    Observed(id = "intro", className = AppStyles.container) {
        Div({ classes(AppStyles.intro) }) {
            Div({ classes(AppStyles.introText) }) {
                Reveal(0) {
                    P({ classes(AppStyles.badge) }) {
                        Span({ classes(AppStyles.badgeDot) }) { Span({ classes(AppStyles.dot) }) }
                        Span({ classes(AppStyles.badgeStrong) }) { Text(tr("Senior Android vývojář", "Senior Android developer")) }
                        Span { Text("·") }
                        Text(tr("Kotlin · Compose · Česko", "Kotlin · Compose · Czechia"))
                    }
                }
                Reveal(1) {
                    H1({ classes(AppStyles.display) }) {
                        Span({ classes(AppStyles.line) }) { Text(tr("Komplexní aplikace.", "Complex apps.")) }
                        Text(" ")
                        Span({ classes(AppStyles.accent) }) { Text(tr("Jasně postavené.", "Clearly built.")) }
                    }
                }
                Reveal(2) {
                    P({ classes(AppStyles.lead) }) {
                        Text(
                            tr(
                                "Propojuji vývoj, produktové myšlení a design do mobilních řešení, která dávají smysl uživatelům i týmům.",
                                "I connect development, product thinking and design into mobile solutions that make sense to users and teams.",
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
                            Text(tr("Probrat spolupráci", "Talk about working together"))
                            MaterialIcon("arrow_forward", AppStyles.buttonIcon)
                        }
                    }
                }
            }

            Reveal(2, AppStyles.visual) { IntroVisual() }
        }
    }
}

/** Photo morphing through Material shapes, framed like a canvas in Android Studio preview. */

@Composable
private fun IntroVisual() {
    var shape by remember { mutableStateOf("") }

    Span({
        classes(AppStyles.frameLabel, AppStyles.frameLabelAccent)
        style {
            property("top", "0")
            property("left", "2%")
        }
    }) { Text("MaterialShapes.$shape") }

    Div({ classes(AppStyles.frame) }) {
        Img(src = "/concept/hero-frame.svg", alt = "") {
            classes(AppStyles.frameLines)
            attr("aria-hidden", "true")
        }
        MorphingPhoto(src = "/weissar.webp", alt = "Petr Weissar") { shape = it }
    }

    Span({ classes(AppStyles.photoBadge) }) {
        Span({ classes(AppStyles.dot) })
        Text(tr("Android · od 2015", "Android · since 2015"))
    }
    Span({
        classes(AppStyles.frameLabel)
        style {
            property("bottom", "0")
            property("left", "3%")
        }
    }) { Text("Kotlin") }
    Span({
        classes(AppStyles.frameLabel)
        style {
            property("bottom", "0")
            property("right", "3%")
        }
    }) { Text("Material 3") }
}
