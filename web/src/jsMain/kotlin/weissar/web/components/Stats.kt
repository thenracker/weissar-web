package weissar.web.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import weissar.web.theme.AppStyles

/** Row of big numbers with a small label under each, [items] are value to label. */
@Composable
fun Stats(items: List<Pair<String, String>>, color: CSSColorValue) {
    Div({
        classes(AppStyles.stats)
        style { color(color) }
    }) {
        items.forEach { (value, label) ->
            Div({ classes(AppStyles.stat) }) {
                Span({ classes(AppStyles.statValue) }) { Text(value) }
                Span({ classes(AppStyles.statLabel) }) { Text(label) }
            }
        }
    }
}
