package weissar.web.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import weissar.web.components.Body
import weissar.web.components.Bullet
import weissar.web.components.Content
import weissar.web.components.Group
import weissar.web.components.Icon
import weissar.web.components.Logo
import weissar.web.components.Section
import weissar.web.components.Spacer
import weissar.web.components.Subtitle
import weissar.web.components.Title
import weissar.web.theme.AppStyles
import weissar.web.theme.Colors

@Composable
fun SvatebkaSection() {
    val textColor = Colors.primaryTextNight

    Section(
        id = "svatebka",
        background = Colors.backgroundSvatebka,
        left = {
            Content {
                Title("Svatebka", textColor)

                Spacer()
                Subtitle("Wedding mobile app", textColor)
                Spacer()

                Group {
                    Bullet("wedding organizer", textColor, subItems = listOf("schedule", "contacts", "map"))
                    Bullet("guest entertainment", textColor, subItems = listOf("challenges/quiz"))
                    Bullet("photos/videos sharing", textColor)
                    Bullet("push notifications", textColor)
                    Bullet("built with Firebase", textColor)
                }
            }
        },
        right = {
            Content {
                Logo(src = "svatebka.svg", alt = "Svatebka", alignEnd = true, hideOnMobile = true)

                Spacer()

                A(href = "https://svatebka.app", attrs = { classes(AppStyles.button, AppStyles.end) }) {
                    Body("Prozkoumat")
                    Icon(src = "arrow-more.svg")
                }
            }
        },
    )
}
