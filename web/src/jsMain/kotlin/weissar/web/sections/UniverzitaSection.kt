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
fun UniverzitaSection() {
    val textColor = Colors.primaryTextNight

    Section(
        id = "univerzita",
        background = Colors.backgroundUniverzita,
        left = {
            Content {
                Title("Univerzita", textColor)

                Spacer()
                Subtitle(tr("Aplikace pro vysokoškoláky", "App for university students"), textColor)
                Spacer()

                Group {
                    Bullet(
                        tr("rozvrh, známky a zkoušky na jednom místě", "timetable, grades & exams in one place"),
                        textColor,
                        subItems = listOf(
                            tr("seznam, tabulka nebo denní agenda", "list, table or daily agenda"),
                            tr("widget na ploše", "home screen widget"),
                            tr("export do kalendáře", "calendar export"),
                        ),
                    )
                    Bullet(tr("notifikace před každou hodinou", "notifications before every lesson"), textColor)
                    Bullet(tr("vyhledávání studentů, učitelů a předmětů", "search for students, teachers & subjects"), textColor)
                    Bullet(tr("průběh studia a kredity", "study progress & credits"), textColor)
                    Bullet(tr("15+ univerzit s IS/STAG a ČVUT", "15+ universities with IS/STAG and ČVUT"), textColor)
                }
            }
        },
        right = {
            Content {
                Logo(src = "univerzita.svg", alt = "Univerzita", alignEnd = true, hideOnMobile = true)

                Spacer()

                A(href = "https://univerzita.app", attrs = { classes(AppStyles.button, AppStyles.end) }) {
                    Body(tr("Prozkoumat", "Explore"))
                    Icon(src = "arrow-more.svg")
                }
            }
        },
    )
}
