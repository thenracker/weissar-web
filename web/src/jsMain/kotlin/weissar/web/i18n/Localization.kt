package weissar.web.i18n

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.browser.document
import kotlinx.browser.localStorage

enum class Language(val code: String) {
    CS("cs"),
    EN("en"),
}

/** Current language of the page, Czech by default, remembered in the browser. */
object Localization {

    private const val STORAGE_KEY = "language"

    var language by mutableStateOf(loadLanguage())
        private set

    init {
        applyToDocument()
    }

    fun select(language: Language) {
        this.language = language
        applyToDocument()
        runCatching { localStorage.setItem(STORAGE_KEY, language.code) }
    }

    /** index.html has the Czech description, it's swapped for the selected language. */
    private fun applyToDocument() {
        document.documentElement?.setAttribute("lang", language.code)
        document.querySelector("meta[name=description]")?.setAttribute(
            "content",
            tr(
                "Senior Android vývojář, softwarový analytik, UX/UI konzultant",
                "Senior Android developer, software analyst, UX/UI consultant",
            ),
        )
    }

    private fun loadLanguage(): Language {
        val code = runCatching { localStorage.getItem(STORAGE_KEY) }.getOrNull()
        return Language.entries.firstOrNull { it.code == code } ?: Language.CS
    }
}

/** Text in both languages, resolved to the current one. */
class Translated(val cs: String, val en: String) {
    val text: String
        get() = tr(cs, en)
}

/** Picks the text for the current language, recomposes when the language changes. */
fun tr(cs: String, en: String): String = when (Localization.language) {
    Language.CS -> cs
    Language.EN -> en
}
