package weissar.web.concept.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import weissar.web.concept.theme.AppStyles
import weissar.web.scroll.SectionObserver

/** Block revealed when it scrolls into view, see [AppStyles.reveal]. */
@Composable
fun Observed(
    id: String? = null,
    className: String? = null,
    content: @Composable () -> Unit,
) {
    Div({
        if (id != null) id(id)
        if (className != null) classes(className)
        ref { element ->
            SectionObserver.observe(element)
            onDispose { SectionObserver.unobserve(element) }
        }
    }) { content() }
}

/** [order] staggers the animation of siblings. */
@Composable
fun Reveal(order: Int = 0, className: String? = null, content: @Composable () -> Unit) {
    Div({
        classes(AppStyles.reveal)
        if (className != null) classes(className)
        style { property("--d", "$order") }
    }) { content() }
}

/** Icon from Material Symbols (Rounded, filled) in resources/concept/icons, colored by the text color. */
@Composable
fun MaterialIcon(name: String, className: String? = null) {
    Span({
        classes(AppStyles.icon)
        if (className != null) classes(className)
        attr("aria-hidden", "true")
        style { property("--icon", "url(/concept/icons/$name.svg)") }
    })
}

@Composable
fun Eyebrow(number: String, text: String) {
    P({ classes(AppStyles.eyebrow) }) { Text("$number / $text") }
}

/** Serif heading, [accentFirst] puts the lime line on top. */
@Composable
fun DisplayTitle(first: String, second: String, accentFirst: Boolean = false) {
    H2({ classes(AppStyles.display) }) {
        Span({ classes(if (accentFirst) AppStyles.accent else AppStyles.line) }) { Text(first) }
        Text(" ")
        Span({ classes(if (accentFirst) AppStyles.line else AppStyles.accent) }) { Text(second) }
    }
}

@Composable
fun Chips(chips: List<String>, dotted: Boolean = false, large: Boolean = false) {
    Div({ classes(AppStyles.chips) }) {
        chips.forEach { Chip(it, dotted, large) }
    }
}

@Composable
fun Chip(text: String, dotted: Boolean = false, large: Boolean = false) {
    Span({
        classes(AppStyles.chip)
        if (large) classes(AppStyles.chipLarge)
    }) {
        if (dotted) Span({ classes(AppStyles.chipDot) })
        Text(text)
    }
}

/** List with ↳ arrows, items divided by lines. */
@Composable
fun ArrowList(items: List<String>) {
    Ul({ classes(AppStyles.arrowList) }) {
        items.forEach { item ->
            Li({ classes(AppStyles.arrowItem) }) {
                MaterialIcon("subdirectory_arrow_right", AppStyles.arrowIcon)
                Text(item)
            }
        }
    }
}

/** Material 3 cookie with an icon, used above the columns. */
@Composable
fun Cookie(icon: String) {
    Div({ classes(AppStyles.cookie) }) { MaterialIcon(icon) }
}

/** Tech names scrolling sideways forever, rendered twice so the loop has no seam. */
@Composable
fun Marquee(items: List<String>) {
    Div({
        classes(AppStyles.marquee)
        attr("aria-label", items.joinToString(", "))
        attr("role", "img")
    }) {
        Div({ classes(AppStyles.marqueeTrack) }) {
            // Two identical halves for a seamless loop, each wider than the widest page
            repeat(4) {
                items.forEach { item ->
                    Span({
                        classes(AppStyles.marqueeItem)
                        attr("aria-hidden", "true")
                    }) {
                        Text(item)
                        Span({ classes(AppStyles.marqueeDot) })
                    }
                }
            }
        }
    }
}

/** Three phones fanned out, screenshots inside. */
@Composable
fun Phones(screens: List<String>, alt: String) {
    Div({ classes(AppStyles.phones) }) {
        screens.forEach { src ->
            Div({ classes(AppStyles.phone) }) {
                Img(src = src, alt = alt) {
                    classes(AppStyles.phoneScreen)
                    attr("loading", "lazy")
                }
            }
        }
    }
}
