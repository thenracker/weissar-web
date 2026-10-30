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
import weissar.web.theme.Colors

@Composable
fun SkillsSection() {
    val textColor = Colors.primaryTextNight

    Section(
        id = "skills",
        background = Colors.backgroundDark,
        left = {
            Content {
                Title("Skills", textColor)

                Spacer()

                Group {
                    Subtitle("Android", textColor) { Icon(src = "android.svg", alt = "Android") }
                    Bullet("native apps development since 2015", textColor)
                    Bullet("Jetpack, Compose, Koin, MVVM, MVI", textColor)
                    Bullet("50+ projects (Java & Kotlin)", textColor)
                }

                Spacer()

                Group {
                    Subtitle("Kotlin", textColor) { Icon(src = "kotlin.svg", alt = "Kotlin") }
                    Bullet("experience since 2017", textColor)
                    Bullet("Coroutines, Flows, Serialization", textColor)
                    Bullet("Ktor server & client", textColor)
                }
            }
        },
        right = {
            Content {
                Logo(src = "pw-logo-white.svg", alt = "PW", alignEnd = true)
            }
        },
    )
}
