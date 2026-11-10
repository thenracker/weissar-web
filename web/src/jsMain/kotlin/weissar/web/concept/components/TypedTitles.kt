package weissar.web.concept.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.concept.theme.AppStyles
import kotlin.random.Random

private const val KEYSTROKE_MS = 70
private const val ERASE_MS = 30
private const val HOLD_MS = 2200L

/**
 * Types [titles] one after another in place, erases each after a moment and loops forever.
 * Waits [startDelayMs] for the intro to come in. With reduced motion all titles are shown at once.
 */
@Composable
fun TypedTitles(titles: List<String>, startDelayMs: Long) {
    val reducedMotion = remember { window.matchMedia("(prefers-reduced-motion: reduce)").matches }
    var index by remember { mutableStateOf(0) }
    var charCount by remember { mutableStateOf(0) }
    // A language change swaps the texts mid-loop
    val current by rememberUpdatedState(titles)

    LaunchedEffect(Unit) {
        if (reducedMotion) return@LaunchedEffect
        delay(startDelayMs)
        while (true) {
            while (charCount < current[index].length) {
                delay((KEYSTROKE_MS * Random.nextDouble(.5, 1.5)).toLong())
                charCount++
            }
            delay(HOLD_MS)
            while (charCount > 0) {
                delay(ERASE_MS.toLong())
                charCount--
            }
            index = (index + 1) % current.size
        }
    }

    Span({
        classes(AppStyles.badgeStrong)
        attr("aria-label", titles.joinToString(", "))
    }) {
        if (reducedMotion) {
            Text(titles.joinToString(" · "))
        } else {
            Span({ attr("aria-hidden", "true") }) { Text(titles[index].take(charCount)) }
            Span({ classes(AppStyles.caret) })
        }
    }
}
