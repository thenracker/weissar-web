package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.concept.components.ArrowList
import weissar.web.concept.components.Chips
import weissar.web.concept.components.MaterialIcon
import weissar.web.concept.components.Observed
import weissar.web.concept.components.Reveal
import weissar.web.concept.theme.AppStyles
import weissar.web.concept.theme.Colors
import weissar.web.i18n.tr

/** Smaller apps under the big products, one card each, side by side. */
@Composable
fun SideProjectsSection() {
    Observed(id = "drobne-projekty", className = AppStyles.block) {
        Div({ classes(AppStyles.container) }) {
            Div({ classes(AppStyles.heading) }) {
                Reveal(0) {
                    H3({ classes(AppStyles.sideHeading) }) {
                        Span({ classes(AppStyles.line) }) { Text(tr("Drobné projekty", "Small projects,")) }
                        Span({ classes(AppStyles.accent) }) { Text(tr("pro radost.", "made for fun.")) }
                    }
                }
                Reveal(1) {
                    P({ classes(AppStyles.note) }) {
                        Text(
                            tr(
                                "Aplikace, které dělají jednu věc a dělají ji dobře. Vznikají po večerech, z vlastní potřeby a z radosti z řemesla.",
                                "Apps that do one thing and do it well. Built in the evenings, out of my own need and for the joy of the craft.",
                            ),
                        )
                    }
                }
            }
        }

        Div({ classes(AppStyles.sideGrid) }) {
            SideProject(
                order = 2,
                icon = "/apps/popcorn-o-clock.webp",
                meta = tr("Android aplikace · 2026", "Android app · 2026"),
                title = "Popcorn o'clock",
                description = tr(
                    "Telefon poslouchá popcorn v mikrovlnce a pípne, až je hotový. Už žádný spálený ani napůl rozpálený sáček.",
                    "Your phone listens to the popcorn in the microwave and beeps when it's done. No more burnt or half-popped bags.",
                ),
                chips = listOf("Android", tr("Zpracování zvuku", "Audio processing"), "Offline"),
                screens = listOf("/screens/popcorn-1.webp", "/screens/popcorn-2.webp"),
                link = "https://play.google.com/store/apps/details?id=cz.weissar.popcorn",
                brand = Brand(Colors.popcorn, Colors.popcornLight, Colors.onPopcorn),
            )
            SideProject(
                order = 3,
                icon = "/apps/prsi.webp",
                meta = tr("Android hra · 2026", "Android game · 2026"),
                title = "Prší",
                description = tr(
                    "Klasické české Prší s mariášovými kartami. Proti botům i online s kamarády, s domácími pravidly a bez reklam.",
                    "The classic Czech card game with traditional Czech cards. Against bots or online with friends, with house rules and no ads.",
                ),
                chips = listOf("Android", "Kotlin Multiplatform", "Firebase"),
                screens = listOf("/screens/prsi-1.webp", "/screens/prsi-2.webp"),
                link = "https://play.google.com/store/apps/details?id=cz.weissar.pony",
                brand = Brand(Colors.prsi, Colors.prsiLight, Colors.onPrsi),
            )
            ComingSoon(order = 4)
        }
    }
}

@Composable
private fun SideProject(
    order: Int,
    icon: String,
    meta: String,
    title: String,
    description: String,
    chips: List<String>,
    screens: List<String>,
    link: String,
    brand: Brand,
) {
    Reveal(order, AppStyles.sideCell) {
        Div({
            classes(AppStyles.sideCard)
            style {
                property("--brand", "${brand.color}")
                property("--brand-light", "${brand.light}")
                property("--on-brand", "${brand.onColor}")
            }
        }) {
            Div({ classes(AppStyles.sideTop) }) {
                Img(src = icon, alt = "") { classes(AppStyles.sideIcon) }
                Div {
                    P({ classes(AppStyles.productMeta, AppStyles.sideMeta) }) { Text(meta) }
                    H3({ classes(AppStyles.sideTitle) }) {
                        A(href = link, attrs = { attr("target", "_blank"); attr("rel", "noopener") }) { Text(title) }
                    }
                }
            }
            P({ classes(AppStyles.note, AppStyles.sideText) }) { Text(description) }
            Chips(chips, dotted = true)
            A(href = link, attrs = {
                classes(AppStyles.button, AppStyles.sideButton)
                attr("target", "_blank")
                attr("rel", "noopener")
            }) {
                MaterialIcon("android", AppStyles.buttonIcon)
                Text("Google Play")
                MaterialIcon("arrow_forward", AppStyles.buttonIcon)
            }
            Div({ classes(AppStyles.miniPhones) }) {
                screens.forEach { src ->
                    Div({ classes(AppStyles.phone, AppStyles.miniPhone) }) {
                        Img(src = src, alt = title) {
                            classes(AppStyles.phoneScreen)
                            attr("loading", "lazy")
                        }
                    }
                }
            }
        }
    }
}

/** Apps that aren't on the store yet, replaced by their own card once they are. */
@Composable
private fun ComingSoon(order: Int) {
    Reveal(order, AppStyles.sideCell) {
        Div({ classes(AppStyles.sideCard, AppStyles.sideSoon) }) {
            P({ classes(AppStyles.badge) }) {
                Span({ classes(AppStyles.dot, AppStyles.pulse) })
                Span({ classes(AppStyles.badgeStrong) }) { Text(tr("Připravuji", "In the works")) }
            }
            H3({ classes(AppStyles.sideTitle) }) { Text(tr("Další už se peče", "More in the oven")) }
            ArrowList(
                listOf(
                    tr("Flip a coin – když se nemůžete rozhodnout", "Flip a coin – for when you just can't decide"),
                ),
            )
        }
    }
}
