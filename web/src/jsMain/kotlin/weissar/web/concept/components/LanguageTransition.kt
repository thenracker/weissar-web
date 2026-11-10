package weissar.web.concept.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Element
import org.w3c.dom.asList
import weissar.web.i18n.Localization

private const val TEXTS = "h1, h2, h3, p, li, a, button, .concept-chip"

/** After a language switch the texts on screen come in again, blurred and rising a little, like on load. */
@Composable
fun LanguageTransition() {
    val shown = remember { arrayOf(Localization.language) }
    LaunchedEffect(Localization.language) {
        if (shown[0] == Localization.language) return@LaunchedEffect
        shown[0] = Localization.language
        if (window.matchMedia("(prefers-reduced-motion: reduce)").matches) return@LaunchedEffect
        textsOnScreen().forEachIndexed { index, element ->
            element.asDynamic().animate(
                arrayOf(
                    js("({ opacity: 0, transform: 'translateY(10px)', filter: 'blur(6px)' })"),
                    js("({ opacity: 1, transform: 'none', filter: 'none' })"),
                ),
                timing(delayMs = minOf(index, 12) * 25),
            )
        }
    }
}

/** Outermost texts in the viewport, so nested ones don't animate twice. */
private fun textsOnScreen(): List<Element> {
    val all = document.querySelectorAll(".concept-page :is($TEXTS)").asList().map { it as Element }
    return all.filter { element ->
        val rect = element.getBoundingClientRect()
        rect.bottom > 0 && rect.top < window.innerHeight && rect.height > 0 &&
            element.parentElement?.closest(TEXTS)?.let { it.closest(".concept-page") == null } != false
    }
}

private fun timing(delayMs: Int): dynamic {
    val options: dynamic = js("({ duration: 600, easing: 'cubic-bezier(.16, 1, .3, 1)', fill: 'backwards' })")
    options.delay = delayMs
    return options
}
