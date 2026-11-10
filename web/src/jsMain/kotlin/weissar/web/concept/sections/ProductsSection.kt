package weissar.web.concept.sections

import androidx.compose.runtime.Composable
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
import weissar.web.i18n.tr

@Composable
fun ProductsSection() {
    Observed(id = "projekty", className = AppStyles.block) {
        Div({ classes(AppStyles.container) }) {
            SectionHeading(
                number = "02",
                eyebrow = tr("Vybrané projekty", "Selected projects"),
                first = tr("Produkty,", "Products"),
                second = tr("které žijí v reálném světě", "living in the real world"),
                note = tr(
                    "Dva dlouhodobé produkty ukazují šíři práce: od multiplatformní služby pro svatební hosty po univerzitní aplikaci pro desítky tisíc studentů.",
                    "Two long-running products show the range of my work: from a multiplatform service for wedding guests to a university app for tens of thousands of students.",
                ),
            )
        }

        Product(
            id = "svatebka",
            meta = tr("Web a mobilní aplikace", "Web & mobile app"),
            since = null,
            title = "Svatebka",
            description = tr(
                "Digitální místo pro všechny informace a společné momenty svatebních hostů.",
                "A digital place for all the information and shared moments of wedding guests.",
            ),
            points = listOf(
                tr("Program, mapa a kontakty bez instalace", "Programme, map & contacts with no install"),
                tr("Galerie od hostů v plné kvalitě", "Guests' gallery in full quality"),
                tr("Soutěže, kvízy a notifikace v reálném čase", "Challenges, quizzes & real-time notifications"),
            ),
            chips = listOf("Android", "iOS", "Web", "Firebase"),
            screens = listOf("/screens/svatebka-1.webp", "/screens/svatebka-2.webp", "/screens/svatebka-3.webp"),
            link = "https://svatebka.app",
        )
        Product(
            id = "univerzita",
            meta = tr("Studentská aplikace", "Student app"),
            since = tr("od roku 2016", "since 2016"),
            title = "Univerzita",
            description = tr(
                "Celé studium v jedné aplikaci — rozvrh, zkoušky, známky, menza i volné učebny.",
                "All of your studies in one app — timetable, exams, grades, canteen and free classrooms.",
            ),
            points = listOf(
                tr("Přes 27 tisíc studentů", "Over 27 thousand students"),
                tr("Více než 15 univerzit", "More than 15 universities"),
            ),
            chips = listOf("Kotlin", "Android", tr("Rozvrhy", "Timetables"), tr("Notifikace", "Notifications")),
            screens = listOf("/screens/univerzita-1.webp", "/screens/univerzita-2.webp", "/screens/univerzita-3.webp"),
            link = "https://univerzita.app",
            mirrored = true,
        )
    }
}

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
    mirrored: Boolean = false,
) {
    Observed(id = id, className = AppStyles.card) {
        Div({
            classes(AppStyles.product)
            if (mirrored) classes(AppStyles.productMirrored)
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
