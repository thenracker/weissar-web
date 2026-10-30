package weissar.web.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import weissar.web.components.Body
import weissar.web.components.Content
import weissar.web.components.Group
import weissar.web.components.Icon
import weissar.web.components.Logo
import weissar.web.components.Overline
import weissar.web.components.Section
import weissar.web.components.Spacer
import weissar.web.components.Title
import weissar.web.theme.AppStyles

@Composable
fun IntroSection() {
    Section(
        id = "intro",
        overlayOnMobile = true,
        left = {
            Content {
                Logo(src = "pw-logo.svg", alt = "PW")

                Spacer()

                Group {
                    Overline("Ing. et Ing.")
                    Title("Petr Weissar")
                }

                Spacer()

                Group {
                    Body("Android senior developer")
                    Body("Software analyst")
                    Body("UX/UI consultant")
                    Body("Lecturer")

                    Div({ classes(AppStyles.fillWidth) }) {
                        A(href = "#skills", attrs = { attr("aria-label", "Scroll down") }) {
                            Icon(src = "arrow-double-down.svg", bounce = true)
                        }
                    }
                }
            }
        },
        right = {
            var photoLoaded by remember { mutableStateOf(false) }

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
