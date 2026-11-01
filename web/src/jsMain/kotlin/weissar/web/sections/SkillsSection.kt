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
                    Bullet(tr("nativní aplikace od 2015, 50+ projektů", "native apps since 2015, 50+ projects"), textColor)
                    Bullet("Jetpack, Compose, Material 3", textColor)
                    Bullet("MVVM, MVI, Clean Architecture", textColor)
                    Bullet("Wear OS", textColor)
                }

                Spacer()

                Group {
                    Subtitle("Kotlin", textColor) { Icon(src = "kotlin.svg", alt = "Kotlin") }
                    Bullet(tr("denně od 2017 – aplikace, backend i web", "daily since 2017 – apps, backend & web"), textColor)
                    Bullet("Coroutines, Flow, Serialization", textColor)
                    Bullet(tr("Ktor server i klient", "Ktor server & client"), textColor)
                    Bullet(tr("Multiplatform – Android, iOS i tenhle web", "Multiplatform – Android, iOS & this website"), textColor)
                }
            }
        },
        right = {
            Content {
                Logo(src = "pw-logo-white.svg", alt = "PW", alignEnd = true, hideOnMobile = true)

                Spacer()

                Group {
                    Subtitle("AI", textColor) { Icon(src = "ai.svg", alt = "AI") }
                    Bullet(tr("AI nástroje pro akceleraci vývoje", "AI tools to accelerate development"), textColor)
                    Bullet("Claude Code, Claude API, MCP", textColor)
                    Bullet(tr("TensorFlow a neuronové sítě", "TensorFlow & neural networks"), textColor)
                }
            }
        },
    )
}
