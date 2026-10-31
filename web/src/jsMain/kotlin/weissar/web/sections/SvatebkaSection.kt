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
import weissar.web.i18n.tr
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
                Subtitle(tr("Svatební mobilní aplikace", "Wedding mobile app"), textColor)
                Spacer()

                Group {
                    Bullet(
                        tr("organizace svatby", "wedding organizer"),
                        textColor,
                        subItems = listOf(tr("harmonogram", "schedule"), tr("kontakty", "contacts"), tr("mapa", "map")),
                    )
                    Bullet(
                        tr("zábava pro hosty", "guest entertainment"),
                        textColor,
                        subItems = listOf(tr("výzvy/kvíz", "challenges/quiz")),
                    )
                    Bullet(tr("sdílení fotek a videí", "photos/videos sharing"), textColor)
                    Bullet(tr("push notifikace", "push notifications"), textColor)
                    Bullet(tr("postaveno na Firebase", "built with Firebase"), textColor)
                }
            }
        },
        right = {
            Content {
                Logo(src = "svatebka.svg", alt = "Svatebka", alignEnd = true, hideOnMobile = true)

                Spacer()

                A(href = "https://svatebka.app", attrs = { classes(AppStyles.button, AppStyles.end) }) {
                    Body(tr("Prozkoumat", "Explore"))
                    Icon(src = "arrow-more.svg")
                }
            }
        },
    )
}
