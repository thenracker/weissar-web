package weissar.web.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import weissar.web.theme.AppStyles

// Centers of the round ends of the five strokes in pw-logo.svg (viewBox 220x110)
private val strokes = listOf(
    "M16.5 16.5 L44 93.5",
    "M60.5 16.5 L73.89 55.04",
    "M110 16.5 L137.5 93.5",
    "M154 16.5 L181.5 93.5",
    "M198 16.5 L203.5 33",
)

/**
 * PW logo drawn as five round strokes – they start as dots in the top left corner
 * and stretch to the bottom right when the section is revealed.
 * With [loop] they keep shrinking back to dots and stretching again.
 */
@Composable
fun AnimatedLogo(color: String = "#222", loop: Boolean = false, alignEnd: Boolean = false) {
    Div({
        classes(AppStyles.logo, AppStyles.logoAnimated, if (loop) AppStyles.logoLoop else AppStyles.logoIntro)
        if (alignEnd) classes(AppStyles.end)
        attr("role", "img")
        attr("aria-label", "PW")
        ref { element ->
            element.innerHTML = buildString {
                append("""<svg viewBox="0 0 220 110" fill="none" stroke="$color" stroke-width="33" stroke-linecap="round">""")
                strokes.forEachIndexed { index, d ->
                    append("""<path class="${AppStyles.logoStroke}" style="--s: $index" pathLength="1" d="$d"/>""")
                }
                append("</svg>")
            }
            onDispose { }
        }
    })
}
