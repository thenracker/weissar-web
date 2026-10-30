package weissar.web.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.vw
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H6
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import weissar.web.theme.Colors

@Composable
fun Title(text: String, color: CSSColorValue = Colors.primaryText) {
    H1({
        style {
            fontSize(4.vw)
            fontWeight(700)
            color(color)
        }
    }) { Text(text) }
}

@Composable
fun Subtitle(
    text: String,
    color: CSSColorValue = Colors.primaryText,
    trailing: (@Composable () -> Unit)? = null,
) {
    H2({
        style {
            fontSize(3.vw)
            fontWeight(700)
            color(color)
        }
    }) {
        Text(text)
        if (trailing != null) {
            Text(" ")
            trailing()
        }
    }
}

@Composable
fun Overline(text: String, color: CSSColorValue = Colors.secondaryText) {
    H6({
        style {
            fontSize(2.vw)
            fontWeight(400)
            color(color)
        }
    }) { Text(text) }
}

@Composable
fun Body(text: String, color: CSSColorValue = Colors.primaryText, small: Boolean = false) {
    P({
        style {
            fontSize(if (small) 1.5.vw else 2.vw)
            fontWeight(400)
            color(color)
        }
    }) { Text(text) }
}

/** Bullet point with optional indented sub items. */
@Composable
fun Bullet(text: String, color: CSSColorValue, subItems: List<String> = emptyList()) {
    Body("• $text", color)
    subItems.forEach { Body("    - $it", color, small = true) }
}
