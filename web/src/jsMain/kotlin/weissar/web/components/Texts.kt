package weissar.web.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H6
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import weissar.web.theme.AppStyles
import weissar.web.theme.Colors

@Composable
fun Title(text: String, color: CSSColorValue = Colors.primaryText) {
    H1({
        classes(AppStyles.title)
        style { color(color) }
    }) { Text(text) }
}

@Composable
fun Subtitle(
    text: String,
    color: CSSColorValue = Colors.primaryText,
    trailing: (@Composable () -> Unit)? = null,
) {
    H2({
        classes(AppStyles.subtitle)
        style { color(color) }
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
        classes(AppStyles.overline)
        style { color(color) }
    }) { Text(text) }
}

@Composable
fun Body(text: String, color: CSSColorValue = Colors.primaryText, small: Boolean = false) {
    P({
        classes(if (small) AppStyles.bodySmall else AppStyles.body)
        style { color(color) }
    }) { Text(text) }
}

/** Bullet point with optional indented sub items. */
@Composable
fun Bullet(text: String, color: CSSColorValue, subItems: List<String> = emptyList(), small: Boolean = false) {
    Body("• $text", color, small)
    subItems.forEach { Body("    - $it", color, small = true) }
}
