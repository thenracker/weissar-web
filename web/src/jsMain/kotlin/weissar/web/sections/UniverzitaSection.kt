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
import weissar.web.components.Stats
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
                Subtitle(tr("Celé studium v jedné aplikaci", "Your whole studies in one app"), textColor)
                Body(
                    tr(
                        "Začalo to v roce 2016 aplikací na hledání volných učeben na UHK. Dnes má rozvrh, známky, zkoušky i menzu v kapse přes 27 000 studentů z 15+ vysokých škol.",
                        "It started in 2016 as an app for finding free classrooms at UHK. Today 27,000+ students from 15+ universities carry their timetable, grades, exams and canteen menu in their pocket.",
                    ),
                    textColor,
                    small = true,
                )
                Spacer()

                Group {
                    Bullet(tr("rozvrh jako seznam, tabulka, agenda i widget", "timetable as a list, table, agenda or widget"), textColor)
                    Bullet(tr("zápis na zkoušky včetně čekací fronty", "exam sign-ups incl. a waiting queue"), textColor)
                    Bullet(tr("upozornění na nové známky a termíny", "alerts for new grades and exam dates"), textColor)
                    Bullet(tr("rozvrhy přátel, menza a volné místnosti", "friends' timetables, canteen menus & free rooms"), textColor)
                }

                Spacer()

                Stats(
                    listOf(
                        "27k+" to tr("studentů", "students"),
                        "15+" to tr("univerzit", "universities"),
                        "2016" to tr("první verze", "first version"),
                    ),
                    textColor,
                )
            }
        },
        right = {
            Content {
                Logo(src = "univerzita.svg", alt = "Univerzita", alignEnd = true, hideOnMobile = true)

                Spacer()
                Screenshots(listOf("screens/univerzita-1.webp", "screens/univerzita-2.webp", "screens/univerzita-3.webp"), alt = "Univerzita")
                Spacer()

                A(href = "https://univerzita.app", attrs = { classes(AppStyles.button, AppStyles.end) }) {
                    Body(tr("Prozkoumat", "Explore"))
                    Icon(src = "arrow-more.svg")
                }
            }
        },
    )
}
