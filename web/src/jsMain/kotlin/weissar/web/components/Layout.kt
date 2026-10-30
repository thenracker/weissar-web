package weissar.web.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import weissar.web.scroll.SectionObserver
import weissar.web.theme.AppStyles

/**
 * Full-screen section split into left and right half.
 * On mobile the halves are stacked, [reverseOnMobile] puts the right one on top.
 */
@Composable
fun Section(
    id: String,
    background: CSSColorValue? = null,
    reverseOnMobile: Boolean = false,
    left: @Composable () -> Unit,
    right: @Composable () -> Unit,
) {
    Div({
        id(id)
        classes(AppStyles.section)
        if (reverseOnMobile) classes(AppStyles.sectionReverse)
        if (background != null) style { backgroundColor(background) }
        ref { element ->
            SectionObserver.observe(element)
            onDispose { SectionObserver.unobserve(element) }
        }
    }) {
        Div({ classes(AppStyles.half) }) { left() }
        Div({ classes(AppStyles.half) }) { right() }
    }
}

/** Padded column filling the whole half, its items are revealed one by one. */
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
    Div({ classes(AppStyles.group) }) { content() }
}

@Composable
fun Logo(src: String, alt: String, alignEnd: Boolean = false) {
    Img(src = src, alt = alt) {
        classes(AppStyles.logo)
        if (alignEnd) classes(AppStyles.end)
    }
}

@Composable
fun Icon(src: String, alt: String = "", bounce: Boolean = false) {
    Img(src = src, alt = alt) {
        classes(AppStyles.icon)
        if (bounce) classes(AppStyles.bounceIcon)
    }
}
