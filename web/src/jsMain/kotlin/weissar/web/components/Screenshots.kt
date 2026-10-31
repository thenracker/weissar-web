package weissar.web.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import weissar.web.theme.AppStyles

/** App screenshots fanned out like cards in a hand, on phones they scroll sideways. */
@Composable
fun Screenshots(sources: List<String>, alt: String) {
    Div({ classes(AppStyles.screenshots) }) {
        sources.forEach { src ->
            Img(src = src, alt = alt) {
                classes(AppStyles.screenshot)
                attr("loading", "lazy")
            }
        }
    }
}
