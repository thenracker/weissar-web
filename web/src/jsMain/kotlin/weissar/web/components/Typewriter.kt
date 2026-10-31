package weissar.web.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.theme.AppStyles
import kotlin.random.Random

// ~400 keystrokes per minute
private const val KEYSTROKE_MS = 150
private const val ENTER_MS = 350

/**
 * Types [lines] one by one at the bottom of a fixed box with a blinking cursor,
 * every finished line is pushed up by the next one like in a terminal.
 * Once typed, a language change just swaps the texts.
 */
@Composable
fun Typewriter(lines: List<String>, startDelayMs: Int) {
    var finished by remember { mutableStateOf(prefersReducedMotion()) }
    var lineCount by remember { mutableStateOf(1) }
    var charCount by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        if (finished) return@LaunchedEffect
        delay(startDelayMs.toLong())
        lines.forEachIndexed { index, line ->
            if (index > 0) {
                delay(ENTER_MS.toLong())
                lineCount = index + 1
                charCount = 0
            }
            for (count in 1..line.length) {
                // Human-like rhythm, not a metronome
                delay((KEYSTROKE_MS * Random.nextDouble(0.5, 1.5)).toLong())
                charCount = count
            }
        }
        finished = true
    }

    Div({
        classes(AppStyles.typewriter)
        attr("aria-label", lines.joinToString(", "))
    }) {
        val shown = if (finished) lines else lines.take(lineCount)
        shown.forEachIndexed { index, line ->
            val isLast = index == shown.lastIndex
            P({
                classes(AppStyles.body, AppStyles.typewriterLine)
                attr("aria-hidden", "true")
            }) {
                Text(if (isLast && !finished) line.take(charCount) else line)
                if (isLast) Span({ classes(AppStyles.cursor) }) { Text("_") }
            }
        }
    }
}

private fun prefersReducedMotion() = window.matchMedia("(prefers-reduced-motion: reduce)").matches
