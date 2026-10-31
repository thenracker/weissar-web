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

// ~500 keystrokes per minute
private const val KEYSTROKE_MS = 120
private const val ENTER_MS = 350

/**
 * Types [lines] one by one at the bottom of a fixed box with a blinking cursor,
 * every finished line is pushed up by the next one like in a terminal.
 * After a [finalePauseMs] pause one more [finale] line is typed.
 * Once typed, a language change just swaps the texts.
 */
@Composable
fun Typewriter(lines: List<String>, startDelayMs: Int, finale: String? = null, finalePauseMs: Int = 0) {
    val allLines = lines + listOfNotNull(finale)
    var finished by remember { mutableStateOf(prefersReducedMotion()) }
    var lineCount by remember { mutableStateOf(1) }
    var charCount by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        if (finished) return@LaunchedEffect
        delay(startDelayMs.toLong())
        allLines.forEachIndexed { index, line ->
            if (index > 0) {
                delay(if (index == lines.size) finalePauseMs.toLong() else ENTER_MS.toLong())
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
        classes(AppStyles.body, AppStyles.typewriter)
        // Room for all the lines, so nothing jumps when the next one comes
        style { property("height", "${allLines.size * 1.25}em") }
        attr("aria-label", allLines.joinToString(", "))
    }) {
        val shown = if (finished) allLines else allLines.take(lineCount)
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
