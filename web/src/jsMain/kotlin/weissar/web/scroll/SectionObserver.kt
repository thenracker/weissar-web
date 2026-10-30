package weissar.web.scroll

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.w3c.dom.Element
import weissar.web.theme.AppStyles
import kotlin.js.json

/**
 * Watches sections while scrolling:
 * - marks a section [AppStyles.visible] the first time it scrolls into view (starts its animations)
 * - keeps [activeSection] in sync with the section in the middle of the screen
 */
object SectionObserver {

    var activeSection by mutableStateOf<String?>(null)
        private set

    private val revealObserver = IntersectionObserver(
        callback = { entries, observer ->
            entries.filter { it.isIntersecting }.forEach {
                it.target.classList.add(AppStyles.visible)
                observer.unobserve(it.target)
            }
        },
        options = json("threshold" to 0.2),
    )

    // Zero-height line in the middle of the viewport
    private val activeObserver = IntersectionObserver(
        callback = { entries, _ ->
            entries.filter { it.isIntersecting }.forEach { activeSection = it.target.id }
        },
        options = json("rootMargin" to "-50% 0px -50% 0px"),
    )

    fun observe(section: Element) {
        revealObserver.observe(section)
        activeObserver.observe(section)
    }

    fun unobserve(section: Element) {
        revealObserver.unobserve(section)
        activeObserver.unobserve(section)
    }
}

private external class IntersectionObserver(
    callback: (entries: Array<IntersectionObserverEntry>, observer: IntersectionObserver) -> Unit,
    options: dynamic = definedExternally,
) {
    fun observe(target: Element)
    fun unobserve(target: Element)
}

private external interface IntersectionObserverEntry {
    val target: Element
    val isIntersecting: Boolean
}
