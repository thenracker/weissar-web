package weissar.web.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Nav
import weissar.web.scroll.SectionObserver
import weissar.web.theme.AppStyles

/**
 * Fixed dot navigation on the right side, highlights the section currently on screen.
 * Dots turn dark on sections from [lightSectionIds].
 */
@Composable
fun SectionDots(sectionIds: List<String>, lightSectionIds: Set<String> = emptySet()) {
    Nav({
        classes(AppStyles.dots)
        if (SectionObserver.activeSection in lightSectionIds) classes(AppStyles.dotsDark)
    }) {
        sectionIds.forEach { id ->
            A(href = "#$id", attrs = {
                classes(AppStyles.dot)
                if (SectionObserver.activeSection == id) classes(AppStyles.dotActive)
                attr("aria-label", id)
            })
        }
    }
}
