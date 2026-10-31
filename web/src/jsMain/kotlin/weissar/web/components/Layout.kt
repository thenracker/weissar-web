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
 * On mobile the halves are stacked, [overlayOnMobile] lays the left one over the right one.
 */
@Composable
fun Section(
    id: String,
    background: CSSColorValue? = null,
    overlayOnMobile: Boolean = false,
    left: @Composable () -> Unit,
    right: @Composable () -> Unit,
) {
    Div({
        id(id)
        classes(AppStyles.section)
        if (overlayOnMobile) classes(AppStyles.sectionOverlay)
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

/** Full-screen section without halves, [content] spans the whole width. */
@Composable
fun FullSection(
    id: String,
    background: CSSColorValue? = null,
    content: @Composable () -> Unit,
) {
    Div({
        id(id)
        classes(AppStyles.section, AppStyles.sectionFull)
        if (background != null) style { backgroundColor(background) }
        ref { element ->
            SectionObserver.observe(element)
            onDispose { SectionObserver.unobserve(element) }
        }
    }) {
        content()
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
fun Logo(src: String, alt: String, alignEnd: Boolean = false, hideOnMobile: Boolean = false) {
    Img(src = src, alt = alt) {
        classes(AppStyles.logo)
        if (alignEnd) classes(AppStyles.end)
        if (hideOnMobile) classes(AppStyles.hideOnMobile)
    }
}

@Composable
fun Icon(src: String, alt: String = "", bounce: Boolean = false) {
    Img(src = src, alt = alt) {
        classes(AppStyles.icon)
        if (bounce) classes(AppStyles.bounceIcon)
    }
}
