package weissar.web.concept.sections

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.concept.components.ArrowList
import weissar.web.concept.components.Chips
import weissar.web.concept.components.Observed
import weissar.web.concept.components.Phones
import weissar.web.concept.components.Reveal
import weissar.web.concept.components.SectionHeading
import weissar.web.concept.theme.AppStyles
import weissar.web.concept.theme.Colors
import weissar.web.i18n.tr

@Composable
fun ProductsSection() {
    Observed(id = "projekty", className = AppStyles.block) {
        Div({ classes(AppStyles.container) }) {
            SectionHeading(
                number = "02",
                eyebrow = tr("Vlastní projekty", "My own projects"),
                first = tr("Moje aplikace,", "My apps,"),
                second = tr("od nápadu po tisíce uživatelů.", "from an idea to thousands of users."),
            )
        }

        Product(
            id = "svatebka",
            meta = tr("Web a mobilní aplikace", "Web & mobile app"),
            since = null,
            title = "Svatebka",
            description = tr(
                "Svatební web a aplikace pro hosty. Všechno, co hosté potřebují vědět, a fotky z celého dne na jednom místě.",
                "Wedding website & guest app. Everything the guests need to know and photos from the whole day in one place.",
            ),
            points = listOf(
                tr("Program, mapa a kontakty na webu bez instalace", "Programme, map & contacts on the web, no install needed"),
                tr("Galerie od hostů v plné kvalitě", "Guests' gallery in full quality"),
                tr("Svatební soutěž a kvíz o novomanželích", "Wedding challenges & a quiz about the couple"),
            ),
            chips = listOf("Android", "iOS", "Web", "Firebase"),
            screens = listOf("/screens/svatebka-1.webp", "/screens/svatebka-2.webp", "/screens/svatebka-3.webp"),
            link = "https://svatebka.app",
            brand = Brand(Colors.svatebka, Colors.svatebkaLight, Colors.onSvatebka),
        )
        Product(
            id = "univerzita",
            meta = tr("Studentská aplikace", "Student app"),
            since = tr("od roku 2016", "since 2016"),
            title = "Univerzita",
            description = tr(
                "Celé studium v jedné aplikaci. Začalo to hledáním volných učeben na UHK, dnes ji používá přes 27 000 studentů z 15+ vysokých škol.",
                "Your entire degree in one app. It started as a way to find free classrooms at UHK, today 27,000+ students from 15+ universities use it.",
            ),
            points = listOf(
                tr("Rozvrh jako seznam, tabulka, agenda i widget", "Timetable as a list, table, agenda or widget"),
                tr("Zápis na zkoušky včetně čekací fronty", "Exam sign-ups incl. a waiting list"),
                tr("Upozornění na nové známky a termíny", "Alerts for new marks and exam dates"),
            ),
            chips = listOf("Kotlin", "Android", tr("Rozvrhy", "Timetables"), tr("Notifikace", "Notifications")),
            screens = listOf("/screens/univerzita-1.webp", "/screens/univerzita-2.webp", "/screens/univerzita-3.webp"),
            link = "https://univerzita.app",
            brand = Brand(Colors.univerzita, Colors.univerzitaLight, Colors.onUniverzita),
            mirrored = true,
        )
    }
}

/** App's brand [color] for the chips, [light] variant readable as text on the dark card. */
private class Brand(val color: CSSColorValue, val light: CSSColorValue, val onColor: CSSColorValue)

@Composable
private fun Product(
    id: String,
    meta: String,
    since: String?,
    title: String,
    description: String,
    points: List<String>,
    chips: List<String>,
    screens: List<String>,
    link: String,
    brand: Brand,
    mirrored: Boolean = false,
) {
    Observed(id = id, className = AppStyles.card) {
        Div({
            classes(AppStyles.product)
            if (mirrored) classes(AppStyles.productMirrored)
            style {
                property("--brand", "${brand.color}")
                property("--brand-light", "${brand.light}")
                property("--on-brand", "${brand.onColor}")
            }
        }) {
            Div({ classes(AppStyles.productText) }) {
                Reveal(0) {
                    P({ classes(AppStyles.productMeta) }) {
                        Span({ classes(AppStyles.productMetaStrong) }) { Text(meta) }
                        if (since != null) Text(" · $since")
                    }
                }
                Reveal(1) {
                    H3({ classes(AppStyles.productTitle) }) {
                        A(href = link, attrs = { attr("target", "_blank"); attr("rel", "noopener") }) { Text(title) }
                    }
                }
                Reveal(2) { P({ classes(AppStyles.lead) }) { Text(description) } }
                Reveal(3) { ArrowList(points) }
                Reveal(4) { Chips(chips, dotted = true, large = true) }
            }
            Phones(screens, alt = title)
        }
    }
}
