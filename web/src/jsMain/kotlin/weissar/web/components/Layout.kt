package weissar.web.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import weissar.web.theme.AppStyles

/** Full-screen section split into left and right half. */
@Composable
fun Section(
    id: String,
    background: CSSColorValue? = null,
    left: @Composable () -> Unit,
    right: @Composable () -> Unit,
) {
    Div({
        id(id)
        classes(AppStyles.section)
        if (background != null) style { backgroundColor(background) }
    }) {
        Div({ classes(AppStyles.half) }) { left() }
        Div({ classes(AppStyles.half) }) { right() }
    }
}

/** Padded column filling the whole half. */
@Composable
fun Content(content: @Composable () -> Unit) {
    Div({ classes(AppStyles.content) }) { content() }
}

@Composable
fun Spacer() {
    Div({ classes(AppStyles.spacer) })
}

/** Groups items so the [Spacer] doesn't push them apart. */
@Composable
fun Group(content: @Composable () -> Unit) {
    Div { content() }
}

@Composable
fun Logo(src: String, alt: String, alignEnd: Boolean = false) {
    Img(src = src, alt = alt) {
        classes(AppStyles.logo)
        if (alignEnd) classes(AppStyles.end)
    }
}

@Composable
fun Icon(src: String, alt: String = "") {
    Img(src = src, alt = alt) { classes(AppStyles.icon) }
}
