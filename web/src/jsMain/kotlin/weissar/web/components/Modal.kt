package weissar.web.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import weissar.web.i18n.tr
import weissar.web.theme.AppStyles

/** Dialog over the page, closes on the backdrop, the cross or Escape. */
@Composable
fun Modal(label: String, onClose: () -> Unit, content: @Composable () -> Unit) {
    DisposableEffect(Unit) {
        val onKey: (Event) -> Unit = { if ((it as KeyboardEvent).key == "Escape") onClose() }
        window.addEventListener("keydown", onKey)
        // Page under the modal must not scroll
        document.documentElement?.classList?.add(AppStyles.noScroll)
        onDispose {
            window.removeEventListener("keydown", onKey)
            document.documentElement?.classList?.remove(AppStyles.noScroll)
        }
    }

    Div({
        classes(AppStyles.modalBackdrop)
        onClick { onClose() }
    }) {
        Div({
            classes(AppStyles.modal)
            attr("role", "dialog")
            attr("aria-modal", "true")
            attr("aria-label", label)
            onClick { it.stopPropagation() }
        }) {
            Button({
                classes(AppStyles.modalClose)
                attr("aria-label", tr("Zavřít", "Close"))
                onClick { onClose() }
            }) { Text("✕") }

            content()
        }
    }
}
