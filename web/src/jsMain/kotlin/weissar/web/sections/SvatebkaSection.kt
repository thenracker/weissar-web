package weissar.web.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import weissar.web.components.Body
import weissar.web.components.Bullet
import weissar.web.components.Content
import weissar.web.components.Group
import weissar.web.components.Icon
import weissar.web.components.Logo
import weissar.web.components.Screenshots
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
                Subtitle(tr("Svatební web a aplikace pro hosty", "Wedding website & guest app"), textColor)
                Body(
                    tr(
                        "Program, mapa, kontakty, společné fotky i zábava – všechno, co hosté na svatbě potřebují, na jednom místě. Více zábavy, více zážitků, méně organizace.",
                        "Programme, map, contacts, shared photos and fun – everything wedding guests need, in one place. More fun, more memories, less organising.",
                    ),
                    textColor,
                    small = true,
                )
                Spacer()

                Group {
                    Bullet(tr("program, mapa a kontakty na webu bez instalace", "programme, map & contacts on the web, no install needed"), textColor)
                    Bullet(tr("galerie od hostů v plné kvalitě", "guests' gallery in full quality"), textColor)
                    Bullet(tr("svatební soutěž a kvíz o novomanželích", "wedding challenges & a quiz about the couple"), textColor)
                    Bullet(tr("notifikace hostům v reálném čase", "real-time notifications for guests"), textColor)
                    Bullet(tr("Android, iOS a web · Firebase", "Android, iOS & web · Firebase"), textColor)
                }
            }
        },
        right = {
            Content {
                Logo(src = "svatebka.svg", alt = "Svatebka", alignEnd = true, hideOnMobile = true)

                Spacer()
                Screenshots(listOf("screens/svatebka-1.webp", "screens/svatebka-2.webp", "screens/svatebka-3.webp"), alt = "Svatebka")
                Spacer()

                A(href = "https://svatebka.app", attrs = { classes(AppStyles.button, AppStyles.end) }) {
                    Body(tr("Prozkoumat", "Explore"))
                    Icon(src = "arrow-more.svg")
                }
            }
        },
    )
}
