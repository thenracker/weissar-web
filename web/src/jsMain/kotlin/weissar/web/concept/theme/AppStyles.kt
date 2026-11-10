package weissar.web.concept.theme

import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.media
import org.jetbrains.compose.web.css.percent
import weissar.web.theme.AppStyles as LegacyStyles

/**
 * Styles of weissar.dev/concept, kept apart from the old [LegacyStyles] so the old page doesn't change.
 * Sizes follow the design at 1270px width, fluid in between with clamp().
 */
// Own prefix, the old AppStyles is on the page too (contact modal) and would share class names
object AppStyles : StyleSheet(customPrefix = "concept-") {

    private const val TABLET = "(max-width: 1000px)"
    private const val PHONE = "(max-width: 640px)"

    private const val MAX_WIDTH = "1600px"

    private const val SERIF = "'Source Serif 4', Georgia, serif"
    private const val SANS = "Inter, system-ui, sans-serif"

    private val revealUp by keyframes {
        from {
            property("opacity", "0")
            property("transform", "translateY(28px)")
        }
        to {
            property("opacity", "1")
            property("transform", "none")
        }
    }

    private val marqueeScroll by keyframes {
        from { property("transform", "translateX(0)") }
        to { property("transform", "translateX(-50%)") }
    }

    val page by style {
        property("--pad", "clamp(20px, 5vw, 64px)")
        property("--inset", "clamp(10px, 2.8vw, 36px)")
        property("min-height", "100vh")
        // Wider than a 15" laptop, the whole page stays centered with the background around
        property("padding-left", "max(0px, calc((100% - ${MAX_WIDTH}) / 2))")
        property("padding-right", "max(0px, calc((100% - ${MAX_WIDTH}) / 2))")
        property("overflow-x", "clip")
        backgroundColor(Colors.background)
        color(Colors.text)
        property("font-family", SANS)
        property("-webkit-font-smoothing", "antialiased")
    }

    init {
        // AppStyles sets Nunito on every element (needed for the shared contact modal), more specific wins
        ".$page *" style { property("font-family", "inherit") }
        "html:has(.$page)" style { backgroundColor(Colors.background) }
    }

    // region Reveal

    /** Fades in once its section scrolls into view, [revealDelay] staggers siblings. */
    val reveal by style {
        property("opacity", "0")
    }

    init {
        ".${LegacyStyles.visible} .$reveal, .${LegacyStyles.visible}.$reveal" style {
            property("animation", "${revealUp.name} .9s cubic-bezier(.16, 1, .3, 1) both")
            property("animation-delay", "calc(var(--base, 0ms) + var(--d, 0) * 90ms)")
        }
    }

    /** Header and intro come in once the logo has drawn itself (≈ 700 ms). */
    val afterLogo by style {
        property("--base", "650ms")
    }

    // endregion

    // region Layout

    val container by style {
        property("padding", "0 var(--pad)")
        property("max-width", "1440px")
        property("margin", "0 auto")
    }

    val block by style {
        property("padding", "clamp(64px, 9vw, 120px) 0")
        property("scroll-margin-top", "24px")
    }

    /** Rounded surface almost edge to edge. */
    val card by style {
        property("position", "relative")
        property("margin", "0 var(--inset)")
        property("border-radius", "clamp(18px, 2vw, 26px)")
        backgroundColor(Colors.surface)
        property("overflow", "hidden")
        property("scroll-margin-top", "24px")
    }

    init {
        // Ends with a card, the next section's top padding is enough
        ".$block:has(> .$card:last-child)" style { property("padding-bottom", "0") }
    }

    val cardContent by style {
        property("padding", "clamp(48px, 9vw, 120px) clamp(20px, 11vw, 140px)")
        property("max-width", "1440px")
        property("margin", "0 auto")
    }

    /** Title on the left, short text on the right aligned to its bottom. */
    val heading by style {
        property("display", "grid")
        property("grid-template-columns", "1fr 1fr")
        property("gap", "24px clamp(24px, 5vw, 80px)")
        property("align-items", "end")
        property("margin-bottom", "clamp(32px, 4vw, 56px)")
    }

    val columns by style {
        property("display", "grid")
        property("grid-template-columns", "repeat(3, 1fr)")
        property("gap", "40px clamp(24px, 3vw, 48px)")
    }

    init {
        media(TABLET) {
            ".$heading" style { property("grid-template-columns", "1fr") }
            ".$columns" style { property("grid-template-columns", "1fr 1fr") }
        }
        media(PHONE) {
            ".$columns" style { property("grid-template-columns", "1fr") }
        }
    }

    // endregion

    // region Typography

    val eyebrow by style {
        property("font-size", "12px")
        property("font-weight", "600")
        color(Colors.textSecondary)
        property("margin-bottom", "clamp(20px, 2.4vw, 32px)")
    }

    val display by style {
        property("font-family", SERIF)
        property("font-weight", "700")
        property("font-size", "clamp(34px, 3.6vw, 56px)")
        property("line-height", "1.08")
        property("letter-spacing", "-.01em")
        property("text-wrap", "balance")
    }

    /** One line of a [display] title. */
    val line by style {
        property("display", "block")
    }

    val accent by style {
        color(Colors.accent)
        property("display", "block")
    }

    val lead by style {
        property("font-family", SERIF)
        property("font-size", "clamp(17px, 1.45vw, 21px)")
        property("line-height", "1.6")
        color(Colors.textSecondary)
        property("max-width", "620px")
        property("margin-top", "20px")
    }

    val note by style {
        property("font-size", "clamp(14px, 1.05vw, 15px)")
        property("line-height", "1.6")
        color(Colors.textSecondary)
        property("max-width", "600px")
    }

    // endregion

    // region Navigation

    val nav by style {
        property("display", "grid")
        property("grid-template-columns", "1fr auto 1fr")
        property("align-items", "center")
        property("padding-top", "clamp(16px, 2vw, 26px)")
        property("padding-bottom", "clamp(16px, 2vw, 26px)")
        property("padding-left", "var(--inset)")
        property("padding-right", "var(--inset)")
    }

    val navLogo by style {
        property("display", "block")
        property("width", "56px")
    }

    init {
        // Old logo is a third of its column wide
        ".$navLogo .${LegacyStyles.logo}" style { property("width", "100%") }
    }

    val navLinks by style {
        property("display", "flex")
        property("gap", "clamp(20px, 2.6vw, 34px)")
    }

    val navLink by style {
        property("font-size", "15px")
        property("font-weight", "600")
        color(Colors.text)
        property("transition", "color .2s ease")
    }

    /** Language flags (shared with the old page) and the contact button. */
    val navEnd by style {
        property("justify-self", "end")
        property("display", "flex")
        property("align-items", "center")
        property("gap", "clamp(12px, 1.6vw, 24px)")
    }

    init {
        // Fixed in the corner on the old page, here part of the header
        ".$navEnd .${LegacyStyles.languageSwitch}" style { property("position", "static") }
        media(PHONE) {
            ".$navEnd .${LegacyStyles.flag} img" style {
                property("width", "24px")
                property("height", "16px")
            }
        }
    }

    init {
        ".$navLink:hover" style { color(Colors.accent) }
        media(TABLET) {
            ".$navLinks" style { property("display", "none") }
            ".$nav" style { property("grid-template-columns", "1fr auto") }
        }
    }

    // endregion

    // region Buttons

    val button by style {
        property("display", "inline-flex")
        property("align-items", "center")
        property("gap", "10px")
        property("padding", "13px 22px")
        property("border", "none")
        property("border-radius", "999px")
        backgroundColor(Colors.accent)
        color(Colors.onAccent)
        property("font-size", "15px")
        property("font-weight", "700")
        property("cursor", "pointer")
        property("transition", "transform .3s cubic-bezier(.34, 1.56, .64, 1), box-shadow .3s ease")
    }

    val textButton by style {
        property("display", "inline-flex")
        property("align-items", "center")
        property("gap", "10px")
        property("padding", "0")
        property("background", "none")
        property("border", "none")
        property("cursor", "pointer")
        color(Colors.accent)
        property("font-size", "15px")
        property("font-weight", "700")
    }

    val buttonIcon by style {
        property("font-size", "20px")
        property("transition", "transform .3s cubic-bezier(.34, 1.56, .64, 1)")
    }

    val actions by style {
        property("display", "flex")
        property("flex-wrap", "wrap")
        property("align-items", "center")
        property("gap", "16px 28px")
        property("margin-top", "clamp(28px, 3vw, 40px)")
    }

    init {
        ".$button:hover" style {
            property("transform", "translateY(-2px)")
            property("box-shadow", "0 10px 28px rgba(240, 133, 122, .25)")
        }
        ".$button:hover .$buttonIcon, .$textButton:hover .$buttonIcon" style {
            property("transform", "translateX(4px)")
        }
    }

    // endregion

    // region Icons, chips, lists

    /** Material Symbols svg used as a mask, so it takes the text color. */
    val icon by style {
        property("display", "inline-block")
        property("flex-shrink", "0")
        property("width", "1em")
        property("height", "1em")
        property("background-color", "currentColor")
        property("-webkit-mask", "var(--icon) center / contain no-repeat")
        property("mask", "var(--icon) center / contain no-repeat")
    }

    /** Material 3 cookie shape with an icon. */
    val cookie by style {
        property("display", "grid")
        property("place-items", "center")
        property("width", "52px")
        property("height", "52px")
        property("background", "url(/concept/cookie.svg) center / contain no-repeat")
        color(Colors.onCookie)
        property("font-size", "22px")
        property("margin-bottom", "28px")
        property("transition", "transform .6s cubic-bezier(.34, 1.56, .64, 1)")
    }

    /** Text grows, so the chips of all columns in a row sit on one bottom line. */
    val column by style {
        property("display", "flex")
        property("flex-direction", "column")
        property("align-items", "flex-start")
    }

    init {
        ".$column > .$note" style { property("flex-grow", "1") }
        ".$column:hover .$cookie" style { property("transform", "rotate(45deg) scale(1.06)") }
        ".$column:hover .$cookie .$icon" style { property("transform", "rotate(-45deg)") }
        ".$cookie .$icon" style { property("transition", "transform .6s cubic-bezier(.34, 1.56, .64, 1)") }
    }

    val columnTitle by style {
        property("font-family", SERIF)
        property("font-weight", "700")
        property("font-size", "clamp(20px, 1.6vw, 22px)")
        property("margin-bottom", "16px")
    }

    val chips by style {
        property("display", "flex")
        property("flex-wrap", "wrap")
        property("gap", "8px")
        property("margin-top", "16px")
    }

    val chip by style {
        property("display", "inline-flex")
        property("align-items", "center")
        property("gap", "8px")
        property("padding", "6px 12px")
        property("border-radius", "999px")
        backgroundColor(Colors.chip)
        color(Colors.chipText)
        property("font-size", "12px")
        property("font-weight", "700")
        property("text-transform", "uppercase")
        property("letter-spacing", ".01em")
    }

    val chipLarge by style {
        property("padding", "9px 16px")
    }

    val chipDot by style {
        property("width", "7px")
        property("height", "7px")
        property("border-radius", "50%")
        property("background-color", "currentColor")
    }

    val arrowList by style {
        property("list-style", "none")
        property("margin-top", "24px")
        property("max-width", "480px")
    }

    val arrowItem by style {
        property("display", "flex")
        property("align-items", "center")
        property("gap", "14px")
        property("padding", "16px 0")
        property("font-size", "15px")
        color(Colors.textSecondary)
    }

    val arrowIcon by style {
        property("font-size", "20px")
        color(Colors.textTertiary)
    }

    init {
        ".$arrowItem + .$arrowItem" style { property("border-top", "1px solid ${Colors.divider}") }
    }

    // endregion

    // region Intro

    /** Header and intro, exactly one screen high (more only if the content doesn't fit). */
    val hero by style {
        property("display", "flex")
        property("flex-direction", "column")
        property("min-height", "100svh")
    }

    init {
        ".$hero > :last-child" style {
            property("flex", "1")
            property("display", "flex")
            property("align-items", "center")
            property("width", "100%")
            property("box-sizing", "border-box")
        }
    }

    val intro by style {
        property("display", "grid")
        property("grid-template-columns", "1.15fr 1fr")
        property("align-items", "center")
        property("gap", "48px")
        property("width", "100%")
        property("padding", "min(4vw, 6svh, 64px) 0")
    }

    val introText by style {
        property("padding-left", "clamp(0px, 2.5vw, 44px)")
    }

    val badge by style {
        property("display", "inline-flex")
        property("align-items", "center")
        property("gap", "10px")
        property("font-size", "13px")
        color(Colors.textSecondary)
        property("margin-bottom", "28px")
    }

    val badgeStrong by style {
        property("font-weight", "600")
        color(Colors.text)
    }

    val dot by style {
        property("flex-shrink", "0")
        property("margin", "0 4px")
        property("width", "8px")
        property("height", "8px")
        property("border-radius", "50%")
        backgroundColor(Colors.accent)
    }

    private val pulseRing by keyframes {
        from { property("box-shadow", "0 0 0 0 ${Colors.accent}99") }
        each(70.percent) { property("box-shadow", "0 0 0 9px ${Colors.accent}00") }
        to { property("box-shadow", "0 0 0 0 ${Colors.accent}00") }
    }

    /** Ring spreading out of the dot, like a "live" indicator. */
    val pulse by style {
        property("animation", "${pulseRing.name} 1.8s cubic-bezier(.4, 0, .6, 1) infinite")
    }

    private val caretBlink by keyframes {
        each(0.percent) { property("opacity", "1") }
        each(50.percent) { property("opacity", "0") }
    }

    val caret by style {
        property("display", "inline-block")
        property("width", "2px")
        property("height", "1.1em")
        property("margin-left", "2px")
        property("vertical-align", "-.18em")
        backgroundColor(Colors.accent)
        property("animation", "${caretBlink.name} 1s step-end infinite")
    }

    val visual by style {
        property("position", "relative")
        // Shrinks on low screens so the intro still fits in one
        property("width", "min(100%, 500px, calc(100svh - 300px))")
        property("min-width", "min(100%, 280px)")
        property("justify-self", "center")
        property("padding", "24px 0")
    }

    val frame by style {
        property("position", "relative")
        property("aspect-ratio", "1")
    }

    val morphingPhoto by style {
        property("position", "absolute")
        property("inset", "0")
    }

    val photoOutline by style {
        property("stroke", "${Colors.accent}")
        property("stroke-width", "2.5")
    }

    init {
        ".$morphingPhoto svg" style {
            property("display", "block")
            property("width", "100%")
            property("height", "100%")
            property("overflow", "visible")
        }
    }

    init {
        media(TABLET) {
            ".$intro" style { property("grid-template-columns", "1fr") }
            ".$introText" style { property("padding-left", "0") }
        }
    }

    // endregion

    // region Contact modal

    init {
        // Shared ContactModal is styled for the old light page, rendered inside .page here
        ".$page .${LegacyStyles.modalBackdrop}" style { property("background-color", "rgba(8, 6, 6, .6)") }
        ".$page .${LegacyStyles.modal}" style {
            backgroundColor(Colors.surface)
            property("border", "1px solid ${Colors.divider}")
            property("border-radius", "26px")
            property("box-shadow", "0 30px 80px rgba(0, 0, 0, .5)")
        }
        ".$page .${LegacyStyles.modalClose}" style {
            backgroundColor(Colors.chip)
            color(Colors.text)
        }
        ".$page .${LegacyStyles.modalTitle}" style {
            property("font-family", SERIF)
            color(Colors.text)
        }
        ".$page .${LegacyStyles.modalText}" style { color(Colors.textSecondary) }
        ".$page .${LegacyStyles.formLabel}" style { color(Colors.textTertiary) }
        ".$page .${LegacyStyles.formInput}" style {
            backgroundColor(Colors.background)
            color(Colors.text)
        }
        ".$page .${LegacyStyles.formInput}:focus" style {
            backgroundColor(Colors.background)
            property("border-color", "${Colors.accent}")
        }
        ".$page .${LegacyStyles.formInput}::placeholder" style { color(Colors.textTertiary) }
        ".$page .${LegacyStyles.formError}" style { color(Colors.accent) }
        ".$page .${LegacyStyles.formSubmit}" style {
            backgroundColor(Colors.accent)
            property("border-radius", "999px")
        }
        ".$page .${LegacyStyles.formSubmit}:hover" style { backgroundColor(Colors.accent) }
        ".$page .${LegacyStyles.formSubmit} .${LegacyStyles.cardText}" style {
            color(Colors.onAccent)
            property("font-size", "15px")
            property("font-weight", "700")
        }
    }

    // endregion

    // region Marquee

    val marquee by style {
        property("overflow", "hidden")
        property("padding", "clamp(12px, 2vw, 24px) 0")
        property("mask-image", "linear-gradient(90deg, transparent, #000 4%, #000 96%, transparent)")
    }

    val marqueeTrack by style {
        property("display", "flex")
        property("width", "max-content")
        property("animation", "${marqueeScroll.name} 40s linear infinite")
    }

    val marqueeItem by style {
        property("display", "flex")
        property("align-items", "center")
        property("font-family", SERIF)
        property("font-weight", "700")
        property("font-size", "clamp(20px, 2vw, 28px)")
        property("white-space", "nowrap")
    }

    val marqueeDot by style {
        property("width", "5px")
        property("height", "5px")
        property("border-radius", "50%")
        backgroundColor(Colors.accent)
        property("margin", "0 clamp(20px, 2.4vw, 32px)")
    }

    init {
        ".$marquee:hover .$marqueeTrack" style { property("animation-play-state", "paused") }
    }

    // endregion

    // region Products

    val product by style {
        property("display", "grid")
        property("grid-template-columns", "1fr 1fr")
        property("min-height", "clamp(420px, 36vw, 520px)")
    }

    val productText by style {
        property("padding", "clamp(40px, 6vw, 80px) clamp(20px, 3vw, 40px) clamp(40px, 6vw, 80px) clamp(20px, 11vw, 140px)")
        property("align-self", "center")
    }

    /** Text on the right, phones on the left. */
    val productMirrored by style {}

    val productTitle by style {
        property("font-family", SERIF)
        property("font-weight", "700")
        property("font-size", "clamp(32px, 3.2vw, 46px)")
        property("color", "var(--brand-light)")
        property("margin-top", "-8px")
    }

    init {
        // Chips in the app's own color
        ".$product .$chip" style {
            property("background-color", "var(--brand)")
            property("color", "var(--on-brand)")
        }
    }

    val productMeta by style {
        property("font-size", "12px")
        color(Colors.textSecondary)
        property("margin-bottom", "16px")
    }

    val productMetaStrong by style {
        property("font-weight", "600")
    }

    /** Clipped so the fanned phones don't cover the text next to them. */
    val phones by style {
        property("position", "relative")
        property("min-height", "320px")
        property("overflow", "hidden")
    }

    val phone by style {
        property("position", "absolute")
        property("width", "clamp(150px, 17vw, 220px)")
        property("aspect-ratio", "9 / 18")
        property("border-radius", "clamp(22px, 2.4vw, 32px)")
        property("border", "4px solid ${Colors.phoneBorder}")
        backgroundColor(Colors.phone)
        property("overflow", "hidden")
        property("box-shadow", "0 20px 60px rgba(0, 0, 0, .35)")
        property("transition", "transform .6s cubic-bezier(.34, 1.4, .64, 1)")
    }

    val phoneScreen by style {
        property("width", "100%")
        property("height", "100%")
        property("object-fit", "cover")
        property("opacity", ".85")
    }

    init {
        ".$productMirrored" style { property("grid-template-columns", "1fr 1fr") }
        ".$productMirrored .$productText" style {
            property("order", "2")
            property("padding", "clamp(40px, 6vw, 80px) clamp(20px, 6vw, 80px) clamp(40px, 6vw, 80px) clamp(20px, 3vw, 40px)")
        }
        ".$productMirrored .$phones" style { property("order", "1") }
        ".$productTitle a" style { property("color", "inherit") }
        ".$card + .$card" style { property("margin-top", "clamp(12px, 1.4vw, 20px)") }

        // Fanned like cards in a hand, overflowing the bottom of the card
        ".$phone:nth-child(1)" style {
            property("left", "8%")
            property("top", "40%")
            property("transform", "rotate(-30deg)")
        }
        ".$phone:nth-child(2)" style {
            property("left", "34%")
            property("top", "18%")
            property("z-index", "1")
        }
        ".$phone:nth-child(3)" style {
            property("left", "64%")
            property("top", "40%")
            property("transform", "rotate(30deg)")
        }
        ".$product:hover .$phone:nth-child(1)" style { property("transform", "rotate(-34deg) translate(-12px, -10px)") }
        ".$product:hover .$phone:nth-child(2)" style { property("transform", "translateY(-14px)") }
        ".$product:hover .$phone:nth-child(3)" style { property("transform", "rotate(34deg) translate(12px, -10px)") }

        media(TABLET) {
            ".$product, .$productMirrored" style { property("grid-template-columns", "1fr") }
            ".$productText, .$productMirrored .$productText" style {
                property("order", "0")
                property("padding", "48px clamp(20px, 6vw, 48px) 8px")
            }
            ".$phones, .$productMirrored .$phones" style {
                property("order", "1")
                property("min-height", "clamp(260px, 60vw, 380px)")
            }
            ".$phone:nth-child(1)" style { property("left", "4%") }
            ".$phone:nth-child(2)" style { property("left", "calc(50% - clamp(75px, 8.5vw, 110px))") }
            ".$phone:nth-child(3)" style { property("left", "auto"); property("right", "4%") }
        }
    }

    // endregion

    // region Education

    val table by style {
        property("margin-top", "clamp(40px, 5vw, 64px)")
        property("max-width", "1140px")
    }

    val row by style {
        property("display", "grid")
        property("grid-template-columns", "1fr 1fr 1fr")
        property("align-items", "center")
        property("gap", "8px 24px")
        property("padding", "18px 0")
        property("font-size", "15px")
        property("line-height", "1.5")
        color(Colors.textSecondary)
    }

    val rowYears by style {
        property("display", "flex")
        property("align-items", "center")
        property("gap", "14px")
        property("font-variant-numeric", "tabular-nums")
    }

    init {
        ".$row + .$row" style { property("border-top", "1px solid ${Colors.divider}") }
        media(PHONE) {
            ".$row" style {
                property("grid-template-columns", "1fr")
                property("gap", "4px")
            }
            ".$row > :not(:first-child)" style { property("padding-left", "34px") }
        }
    }

    // endregion

    // region Contact

    val contact by style {
        property("position", "relative")
        property("display", "grid")
        property("grid-template-columns", "1fr 1fr")
        property("align-items", "center")
        property("gap", "40px")
        property("padding", "clamp(64px, 9vw, 120px) clamp(0px, 2.5vw, 44px) clamp(48px, 6vw, 80px)")
    }

    /** Big logo looping dots <-> strokes like on the old site, drawn in currentColor so hover can change it. */
    val watermark by style {
        property("width", "min(100%, 440px)")
        property("justify-self", "center")
        color(Colors.watermark)
        property("transition", "color .6s ease")
    }

    init {
        ".$watermark .${LegacyStyles.logo}" style { property("width", "100%") }
    }

    val footer by style {
        color(Colors.textSecondary)
        property("display", "flex")
        property("flex-wrap", "wrap")
        property("justify-content", "space-between")
        property("gap", "12px 24px")
        property("padding", "0 clamp(0px, 2.5vw, 44px) clamp(48px, 6vw, 96px)")
        property("font-size", "15px")
    }

    init {
        ".$contact:hover .$watermark" style { color(Colors.chip) }
        media(TABLET) {
            ".$contact" style { property("grid-template-columns", "1fr") }
            ".$watermark" style { property("display", "none") }
        }
        media(PHONE) {
            ".$footer" style { property("flex-direction", "column") }
        }
    }

    // endregion
}
