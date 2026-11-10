package weissar.web.concept.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import weissar.web.concept.data.Column
import weissar.web.concept.theme.AppStyles

/** Eyebrow and title on the left, short note on the right. */
@Composable
fun SectionHeading(
    number: String,
    eyebrow: String,
    first: String,
    second: String,
    note: String,
    accentFirst: Boolean = false,
) {
    Div({ classes(AppStyles.heading) }) {
        Div {
            Reveal(0) { Eyebrow(number, eyebrow) }
            Reveal(1) { DisplayTitle(first, second, accentFirst) }
        }
        Reveal(2) { P({ classes(AppStyles.note) }) { Text(note) } }
    }
}

/** Three columns with a cookie icon, title, text and chips. */
@Composable
fun Columns(columns: List<Column>) {
    Div({ classes(AppStyles.columns) }) {
        columns.forEachIndexed { index, column ->
            Reveal(index + 3, AppStyles.column) {
                Cookie(column.icon)
                H3({ classes(AppStyles.columnTitle) }) { Text(column.title.text) }
                P({ classes(AppStyles.note) }) { Text(column.text.text) }
                Div({ classes(AppStyles.chips) }) {
                    column.chips.forEach { Chip(it.text) }
                }
            }
        }
    }
}
