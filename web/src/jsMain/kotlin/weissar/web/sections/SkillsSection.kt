package weissar.web.sections

import androidx.compose.runtime.Composable
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
import weissar.web.theme.Colors

@Composable
fun SkillsSection() {
    val textColor = Colors.primaryTextNight

    Section(
        id = "skills",
        background = Colors.backgroundDark,
        left = {
            Content {
                Title(tr("Dovednosti", "Skills"), textColor)

                Spacer()

                Group {
                    Subtitle("Android", textColor) { Icon(src = "android.svg", alt = "Android") }
                    Bullet(tr("vývoj nativních aplikací od roku 2015", "native apps development since 2015"), textColor)
                    Bullet("Jetpack, Compose, Koin, MVVM, MVI", textColor)
                    Bullet(tr("50+ projektů (Java & Kotlin)", "50+ projects (Java & Kotlin)"), textColor)
                }

                Spacer()

                Group {
                    Subtitle("Kotlin", textColor) { Icon(src = "kotlin.svg", alt = "Kotlin") }
                    Bullet(tr("zkušenosti od roku 2017", "experience since 2017"), textColor)
                    Bullet("Coroutines, Flows, Serialization", textColor)
                    Bullet(tr("Ktor server & klient", "Ktor server & client"), textColor)
                }
            }
        },
        right = {
            Content {
                Logo(src = "pw-logo-white.svg", alt = "PW", alignEnd = true, hideOnMobile = true)
            }
        },
    )
}
