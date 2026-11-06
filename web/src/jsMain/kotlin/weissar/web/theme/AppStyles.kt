package weissar.web.theme

import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.AlignSelf
import org.jetbrains.compose.web.css.AnimationDirection
import org.jetbrains.compose.web.css.AnimationFillMode
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.alignSelf
import org.jetbrains.compose.web.css.animation
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.boxSizing
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.direction
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.duration
import org.jetbrains.compose.web.css.fillMode
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.flexGrow
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.iterationCount
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.media
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.timingFunction
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.css.vw
import org.jetbrains.compose.web.css.width

// Ratio: 80px from Figma = 4vw
object AppStyles : StyleSheet() {

    // Phones and tablets in portrait – halves are stacked under each other
    private const val MOBILE = "(max-width: 800px), (orientation: portrait) and (max-width: 1100px)"
    private const val DESKTOP = "(min-width: 801px) and (orientation: landscape), (min-width: 1101px)"
    private val easeOutExpo = AnimationTimingFunction.cubicBezier(0.16, 1.0, 0.3, 1.0)

    init {
        "*" style {
            margin(0.px)
            padding(0.px)
            boxSizing("border-box")
            fontFamily("Nunito", "sans-serif")
        }
        "html" style {
            property("scroll-behavior", "smooth")
        }
        "a" style {
            textDecoration("none")
        }
    }

    // region Animations

    private val revealUp by keyframes {
        from {
            opacity(0)
            property("transform", "translateY(32px)")
            property("filter", "blur(6px)")
        }
        to {
            opacity(1)
            property("transform", "none")
            property("filter", "none")
        }
    }

    // The photo fades in where it is, zooming out a bit
    private val photoFade by keyframes {
        from {
            opacity(0)
            property("transform", "scale(1.15)")
        }
        to {
            opacity(1)
            property("transform", "scale(1)")
        }
    }

    // Dots appear, then stretch into the logo strokes
    private val logoDots by keyframes {
        from {
            opacity(0)
            property("stroke-dasharray", "0 2")
        }
        each(30.percent) {
            opacity(1)
            property("stroke-dasharray", "0 2")
        }
        to {
            opacity(1)
            property("stroke-dasharray", "1 2")
        }
    }

    private val fadeIn by keyframes {
        from { opacity(0) }
        to { opacity(1) }
    }

    private val modalIn by keyframes {
        from {
            opacity(0)
            property("transform", "translateY(24px) scale(.97)")
        }
        to {
            opacity(1)
            property("transform", "none")
        }
    }

    // Endless dots <-> logo, holds a moment in both states
    private val logoPulse by keyframes {
        each(0.percent) { property("stroke-dasharray", "0 2") }
        each(15.percent) { property("stroke-dasharray", "0 2") }
        each(85.percent) { property("stroke-dasharray", "1 2") }
        each(100.percent) { property("stroke-dasharray", "1 2") }
    }

    private val bounce by keyframes {
        from { property("translate", "0 0") }
        each(50.percent) { property("translate", "0 10px") }
        to { property("translate", "0 0") }
    }

    // endregion

    // region Layout

    /** Added to a section once it scrolls into view – starts its animations. */
    val visible by style {}

    /** Added to an image once it's downloaded. */
    val loaded by style {}

    val section by style {
        display(DisplayStyle.Flex)
        width(100.percent)
        height(100.vh)
    }

    /** On mobile the right half lies under the left one as a background. */
    val sectionOverlay by style {}

    val sectionFull by style {}

    val half by style {
        width(50.percent)
        height(100.vh)
        position(Position.Relative)
    }

    val content by style {
        padding(5.vw)
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        height(100.percent)
    }

    val group by style {}

    val spacer by style {
        flexGrow(1)
    }

    val photoFrame by style {
        position(Position.Absolute)
        property("inset", "0")
        property("overflow", "hidden")
    }

    val fullImage by style {
        // Hidden until the photo is loaded and its section is on screen
        opacity(0)
        // Keeps the photo on its own GPU layer, otherwise it's repainted (blinks) when the reveal ends
        property("will-change", "transform, opacity")
        property("backface-visibility", "hidden")
        width(100.percent)
        height(100.percent)
        property("object-fit", "cover")
        position(Position.Absolute)
        bottom(0.px)
        right(0.px)
    }

    val fillWidth by style {
        width(100.percent)
        padding(20.px)
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
    }

    val end by style {
        alignSelf(AlignSelf.End)
    }

    // endregion

    // region Typography

    val title by style {
        property("font-size", "4vw")
        fontWeight(700)
    }

    val subtitle by style {
        property("font-size", "3vw")
        fontWeight(700)
    }

    val body by style {
        property("font-size", "2vw")
        fontWeight(400)
    }

    val bodySmall by style {
        property("font-size", "1.5vw")
        fontWeight(400)
    }

    val overline by style {
        property("font-size", "2vw")
        fontWeight(400)
    }

    // endregion

    // region Components

    val logo by style {
        width(33.percent)
    }

    val logoAnimated by style {}

    /** Animated once when the page loads, the rest of the intro waits for it. */
    val logoIntro by style {}

    /** Animated back and forth forever. */
    val logoLoop by style {}

    val logoStroke by style {
        opacity(0)
        property("stroke-dasharray", "0 2")
    }

    val hideOnMobile by style {}

    val icon by style {
        width(32.px)
        property("vertical-align", "middle")
        property("transition", "transform .5s cubic-bezier(.34, 1.56, .64, 1)")
    }

    val bounceIcon by style {
        animation(bounce) {
            duration(2.2.s)
            timingFunction(AnimationTimingFunction.EaseInOut)
            iterationCount(null)
        }
    }

    val button by style {
        property("width", "fit-content")
        property("height", "fit-content")
        padding(10.px, 20.px)
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        gap(10.px)
        borderRadius(20.px)
        backgroundColor(Colors.backgroundLight)
        property("transition", "translate .35s ease, box-shadow .35s ease, background-color .35s ease")
    }

    /** "Write me" over the bottom of the intro photo. */
    val photoButton by style {
        position(Position.Absolute)
        bottom(5.vw)
        property("left", "50%")
        property("transform", "translateX(-50%)")
        backgroundColor(Colors.white)
        property("box-shadow", "0 12px 30px rgba(0, 0, 0, .25)")
        property("z-index", "1")
        // Fades in after the photo
        opacity(0)
    }

    /** Hidden on desktop, the opposite of [hideOnMobile]. */
    val onlyMobile by style {}

    val introActions by style {}

    val dots by style {
        position(Position.Fixed)
        right(28.px)
        top(50.percent)
        property("transform", "translateY(-50%)")
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        gap(14.px)
        property("z-index", "10")
    }

    val dot by style {
        display(DisplayStyle.Block)
        width(10.px)
        height(10.px)
        borderRadius(10.px)
        backgroundColor(Colors.white)
        opacity(0.45)
        property("box-shadow", "0 0 6px rgba(0, 0, 0, .35)")
        property("transition", "height .45s cubic-bezier(.16, 1, .3, 1), opacity .3s ease")
    }

    val dotsDark by style {}

    val dotActive by style {
        height(32.px)
        opacity(1)
    }

    val languageSwitch by style {
        position(Position.Fixed)
        top(24.px)
        right(28.px)
        display(DisplayStyle.Flex)
        gap(8.px)
        property("z-index", "10")
    }

    val flag by style {
        display(DisplayStyle.Block)
        padding(4.px)
        property("background", "none")
        property("border", "none")
        property("cursor", "pointer")
        opacity(0.5)
        property("transition", "opacity .3s ease, scale .3s ease")
    }

    val flagSelected by style {
        opacity(1)
    }

    val languageSwitchDark by style {}

    val contactList by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        gap(14.px)
    }

    val contactRow by style {
        display(DisplayStyle.Flex)
        alignItems(AlignItems.Center)
        gap(18.px)
        property("width", "fit-content")
        color(Colors.white)
    }

    val contactLink by style {}

    val contactIcon by style {
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        property("flex-shrink", "0")
        width(48.px)
        height(48.px)
        borderRadius(50.percent)
        backgroundColor(Color("rgba(255, 255, 255, .08)"))
        property("transition", "background-color .3s ease, scale .3s ease")
    }

    val serviceColumns by style {
        display(DisplayStyle.Grid)
        property("grid-template-columns", "repeat(3, 1fr)")
        gap(4.vw)
        alignItems(AlignItems.Start)
    }

    val serviceColumn by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        gap(16.px)
    }

    val cardGrid by style {
        display(DisplayStyle.Grid)
        property("grid-template-columns", "repeat(auto-fill, minmax(260px, 1fr))")
        gap(20.px)
    }

    val card by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        gap(8.px)
        padding(24.px)
        borderRadius(20.px)
        backgroundColor(Colors.white)
        property("transition", "translate .35s ease, box-shadow .35s ease")
    }

    val screenshots by style {
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        property("height", "clamp(220px, 46vh, 460px)")
    }

    val screenshot by style {
        height(100.percent)
        property("width", "auto")
        borderRadius(18.px)
        property("box-shadow", "0 18px 44px rgba(0, 0, 0, .28)")
        property("transition", "translate .6s cubic-bezier(.34, 1.56, .64, 1), rotate .6s cubic-bezier(.34, 1.56, .64, 1)")
    }

    val stats by style {
        display(DisplayStyle.Flex)
        property("gap", "clamp(20px, 3vw, 56px)")
    }

    val stat by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
    }

    val statValue by style {
        property("font-size", "clamp(28px, 2.6vw, 52px)")
        fontWeight(700)
        property("line-height", "1.1")
    }

    val statLabel by style {
        property("font-size", "clamp(13px, 1vw, 20px)")
        opacity(0.7)
    }

    /** On the root while a modal is open. */
    val noScroll by style {
        property("overflow", "hidden")
    }

    val modalBackdrop by style {
        position(Position.Fixed)
        property("inset", "0")
        property("z-index", "20")
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        padding(24.px)
        backgroundColor(Color("rgba(20, 20, 20, .45)"))
        property("backdrop-filter", "blur(6px)")
        animation(fadeIn) {
            duration(.25.s)
            fillMode(AnimationFillMode.Both)
        }
    }

    val modal by style {
        position(Position.Relative)
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        gap(12.px)
        width(100.percent)
        property("max-width", "620px")
        property("max-height", "calc(100svh - 48px)")
        property("overflow-y", "auto")
        property("overscroll-behavior", "contain")
        padding(32.px)
        borderRadius(24.px)
        backgroundColor(Colors.white)
        property("box-shadow", "0 30px 80px rgba(0, 0, 0, .3)")
        animation(modalIn) {
            duration(.5.s)
            timingFunction(easeOutExpo)
            fillMode(AnimationFillMode.Both)
        }
    }

    val modalClose by style {
        position(Position.Absolute)
        top(16.px)
        right(16.px)
        width(36.px)
        height(36.px)
        borderRadius(50.percent)
        property("border", "none")
        backgroundColor(Colors.backgroundLight)
        color(Colors.primaryText)
        property("font-size", "16px")
        property("cursor", "pointer")
        property("transition", "scale .3s ease, background-color .3s ease")
    }

    val modalHeader by style {
        display(DisplayStyle.Flex)
        alignItems(AlignItems.Center)
        gap(16.px)
        property("padding-right", "40px")
    }

    val modalTitle by style {
        property("font-size", "clamp(24px, 2vw, 34px)")
        fontWeight(700)
        color(Colors.primaryText)
    }

    val modalText by style {
        property("font-size", "clamp(16px, 1.1vw, 20px)")
        property("line-height", "1.5")
        color(Colors.primaryText)
    }

    val modalHeading by style {
        property("font-size", "clamp(13px, .9vw, 16px)")
        fontWeight(700)
        property("text-transform", "uppercase")
        property("letter-spacing", ".08em")
        color(Colors.secondaryText)
        property("margin-top", "8px")
    }

    val modalList by style {
        property("padding-left", "20px")
        property("font-size", "clamp(15px, 1vw, 19px)")
        property("line-height", "1.6")
        color(Colors.primaryText)
    }

    val timeline by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        property("border-left", "2px solid #2222")
        property("margin-left", "6px")
    }

    val timelineItem by style {
        position(Position.Relative)
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        property("padding", "0 0 14px 20px")
    }

    val timelineDate by style {
        property("font-size", "clamp(13px, .9vw, 16px)")
        fontWeight(700)
        color(Colors.secondaryText)
    }

    val timelineText by style {
        property("font-size", "clamp(15px, 1vw, 19px)")
        color(Colors.primaryText)
    }

    val modalLink by style {
        property("margin-top", "8px")
    }

    /** Contact row that opens the form instead of a link. */
    val contactButton by style {
        property("border", "none")
        property("background", "none")
        padding(0.px)
        property("font", "inherit")
        property("cursor", "pointer")
    }

    val form by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        gap(8.px)
    }

    val formLabel by style {
        property("font-size", "clamp(13px, .9vw, 16px)")
        fontWeight(700)
        property("text-transform", "uppercase")
        property("letter-spacing", ".08em")
        color(Colors.secondaryText)
        property("margin-top", "8px")
    }

    val formInput by style {
        width(100.percent)
        boxSizing("border-box")
        padding(12.px, 16.px)
        borderRadius(14.px)
        property("border", "2px solid transparent")
        backgroundColor(Colors.backgroundLight)
        color(Colors.primaryText)
        property("font", "inherit")
        property("font-size", "clamp(16px, 1.1vw, 20px)")
        property("outline", "none")
        property("transition", "border-color .3s ease, background-color .3s ease")
    }

    val formTextArea by style {
        property("min-height", "160px")
        property("resize", "vertical")
        property("line-height", "1.5")
    }

    val formError by style {
        property("font-size", "clamp(14px, 1vw, 18px)")
        color(Color("#B3261E"))
    }

    val formSubmit by style {
        property("margin-top", "12px")
        property("border", "none")
        property("font", "inherit")
        property("cursor", "pointer")
    }

    val companyLogoLarge by style {
        width(72.px)
        height(72.px)
        borderRadius(18.px)
        padding(12.px)
    }

    val cardHeader by style {
        display(DisplayStyle.Flex)
        alignItems(AlignItems.Center)
        gap(14.px)
        property("margin-bottom", "4px")
    }

    val companyLogo by style {
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        property("flex-shrink", "0")
        width(52.px)
        height(52.px)
        padding(9.px)
        borderRadius(14.px)
        backgroundColor(Colors.white)
        property("box-shadow", "inset 0 0 0 1px rgba(0, 0, 0, .08)")
        property("overflow", "hidden")
    }

    val companyLogoFill by style {
        padding(0.px)
        property("box-shadow", "none")
    }

    val cardTitle by style {
        property("font-size", "clamp(20px, 1.6vw, 32px)")
        fontWeight(600)
        color(Colors.primaryText)
    }

    val cardText by style {
        property("font-size", "clamp(15px, 1.1vw, 22px)")
        color(Colors.primaryText)
        flexGrow(1)
    }

    val cardMeta by style {
        property("font-size", "clamp(13px, .9vw, 18px)")
        color(Colors.secondaryText)
    }

    val tags by style {
        display(DisplayStyle.Flex)
        property("flex-wrap", "wrap")
        gap(6.px)
        property("margin-top", "8px")
    }

    val tag by style {
        property("font-size", "clamp(12px, .8vw, 16px)")
        padding(4.px, 10.px)
        borderRadius(999.px)
        backgroundColor(Colors.backgroundLight)
        color(Colors.primaryText)
    }

    private val blink by keyframes {
        from { opacity(1) }
        each(50.percent) { opacity(0) }
        to { opacity(0) }
    }

    /** Fixed box for all the lines, new lines appear at the bottom and push the old ones up. */
    val typewriter by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        justifyContent(JustifyContent.FlexEnd)
        color(Colors.primaryText)
    }

    // New line grows from nothing, so the ones above slide up instead of jumping
    private val lineExpand by keyframes {
        from { property("height", "0") }
        to { property("height", "1.25em") }
    }

    private val finaleExpand by keyframes {
        from {
            property("height", "0")
            property("margin-top", "0")
        }
        to {
            property("height", "1.25em")
            property("margin-top", "1.25em")
        }
    }

    val typewriterLine by style {
        property("line-height", "1.25")
        property("height", "1.25em")
        property("white-space", "pre")
        animation(lineExpand) {
            duration(.22.s)
            timingFunction(AnimationTimingFunction.EaseOut)
        }
    }

    /** Last line of the typewriter, bold with an empty line above. */
    val typewriterFinale by style {
        fontWeight(700)
        property("margin-top", "1.25em")
        animation(finaleExpand) {
            duration(.35.s)
            timingFunction(AnimationTimingFunction.EaseOut)
        }
    }

    val cursor by style {
        property("margin-left", ".04em")
        animation(blink) {
            duration(1.s)
            timingFunction(AnimationTimingFunction.StepEnd)
            iterationCount(null)
        }
    }

    /** Cards on a white section, so they need a grey background. */
    val cardGridMuted by style {}

    val chipGroups by style {
        display(DisplayStyle.Grid)
        property("grid-template-columns", "repeat(auto-fill, minmax(260px, 1fr))")
        property("gap", "28px 32px")
        alignItems(AlignItems.Start)
    }

    val chipLabel by style {
        property("font-size", "clamp(13px, .9vw, 17px)")
        fontWeight(700)
        property("text-transform", "uppercase")
        property("letter-spacing", ".08em")
        color(Colors.secondaryTextNight)
        property("margin-bottom", "12px")
    }

    val chipLabelLight by style {
        color(Colors.secondaryText)
    }

    val chips by style {
        display(DisplayStyle.Flex)
        property("flex-wrap", "wrap")
        gap(8.px)
    }

    val chip by style {
        property("font-size", "clamp(13px, .95vw, 18px)")
        padding(6.px, 13.px)
        borderRadius(999.px)
        backgroundColor(Color("rgba(255, 255, 255, .07)"))
        property("box-shadow", "inset 0 0 0 1px rgba(255, 255, 255, .1)")
        color(Colors.primaryTextNight)
        property("transition", "scale .25s ease, background-color .25s ease")
    }

    /** Highlighted chips – what's hot right now. */
    val chipFeatured by style {
        backgroundColor(Color("rgba(245, 166, 91, .16)"))
        property("box-shadow", "inset 0 0 0 1px rgba(245, 166, 91, .55)")
        color(Colors.white)
    }

    val chipLight by style {
        backgroundColor(Colors.backgroundLight)
        property("box-shadow", "none")
        color(Colors.primaryText)
    }

    // endregion

    init {
        ".$sectionFull > .$content" style {
            width(100.percent)
        }
        // Full sections grow when their content doesn't fit the screen
        ".$sectionFull" style {
            property("height", "auto")
            property("min-height", "100vh")
        }
        ".$sectionFull > .$content" style {
            property("height", "auto")
        }
        ".$sectionFull .$spacer" style {
            property("min-height", "48px")
        }
        ".$cardGrid.$cardGridMuted" style {
            property("grid-template-columns", "repeat(auto-fill, minmax(220px, 1fr))")
        }
        ".$cardGridMuted .$card" style {
            backgroundColor(Color("#f4f4f4"))
        }
        ".$cardGridMuted .$tag" style {
            backgroundColor(Colors.white)
        }
        ".$chip:hover" style {
            property("scale", "1.05")
        }

        // Hover effects
        ".$card" style {
            property("cursor", "pointer")
        }
        ".$card:focus-visible" style {
            property("outline", "2px solid #222")
            property("outline-offset", "3px")
        }
        ".$companyLogoLarge.$companyLogoFill" style {
            padding(0.px)
        }
        ".$timelineItem::before" style {
            property("content", "''")
            position(Position.Absolute)
            property("left", "-7px")
            top(4.px)
            width(12.px)
            height(12.px)
            borderRadius(50.percent)
            backgroundColor(Colors.primaryText)
            property("box-shadow", "0 0 0 3px #fff")
        }
        ".$timelineItem:last-child" style {
            property("padding-bottom", "0")
        }
        ".$modalClose:hover" style {
            property("scale", "1.08")
        }
        ".$formInput:focus" style {
            property("border-color", "#222")
            backgroundColor(Colors.white)
        }
        ".$formInput::placeholder" style {
            color(Colors.secondaryText)
        }
        ".$button.$formSubmit:disabled" style {
            opacity(0.5)
            property("cursor", "default")
            property("translate", "none")
            property("box-shadow", "none")
        }
        ".$modalLink:hover" style {
            backgroundColor(Colors.backgroundLight)
            property("box-shadow", "0 10px 24px rgba(0, 0, 0, .12)")
        }
        ".$card:hover" style {
            property("translate", "0 -4px")
            property("box-shadow", "0 14px 32px rgba(0, 0, 0, .1)")
        }
        ".$companyLogo img" style {
            width(100.percent)
            height(100.percent)
            property("object-fit", "contain")
        }
        ".$companyLogoFill img" style {
            property("object-fit", "cover")
        }
        // Section logos fit a square, so tall ones aren't bigger than wide ones
        "img.$logo" style {
            property("aspect-ratio", "1")
            property("object-fit", "contain")
            property("object-position", "left top")
        }
        "img.$logo.$end" style {
            property("object-position", "right top")
        }
        ".$flag img" style {
            display(DisplayStyle.Block)
            width(30.px)
            height(20.px)
            property("filter", "drop-shadow(0 0 3px rgba(0, 0, 0, .35))")
            property("transition", "filter .3s ease")
        }
        ".$flag:hover" style {
            opacity(0.85)
            property("scale", "1.06")
        }
        ".$flagSelected:hover" style {
            opacity(1)
        }
        ".$languageSwitchDark .$flag img" style {
            // white outline -> #222
            property("filter", "invert(.87)")
        }
        ".$contactIcon img" style {
            width(22.px)
            height(22.px)
        }
        ".$contactLink:hover .$contactIcon" style {
            backgroundColor(Color("rgba(255, 255, 255, .18)"))
            property("scale", "1.08")
        }
        ".$dotsDark .$dot" style {
            backgroundColor(Colors.primaryText)
            property("box-shadow", "none")
        }
        ".$button:hover" style {
            property("translate", "0 -4px")
            property("box-shadow", "0 14px 32px rgba(0, 0, 0, .2)")
            backgroundColor(Colors.white)
        }
        ".$button .$icon" style {
            property("transition", "transform .35s ease")
        }
        ".$button:hover .$icon" style {
            property("transform", "translateX(6px)")
        }
        ".$subtitle:hover .$icon" style {
            property("transform", "rotate(-12deg) scale(1.2)")
        }
        ".$dot:hover" style {
            opacity(0.8)
        }

        // Staggered reveal – every item in a column fades in a bit after the previous one
        for (i in 1..12) {
            ".$content > :nth-child($i)" style { property("--i", "$i") }
            ".$group > :nth-child($i)" style { property("--j", "$i") }
        }
        ".$half + .$half" style { property("--base", "250ms") }
        ".$content > :not(.$group), .$group > *" style {
            opacity(0)
        }
        ".$content > :not(.$group)" style {
            property("--delay", "calc(var(--base, 0ms) + var(--i) * 110ms)")
        }
        ".$group > *" style {
            property("--delay", "calc(var(--base, 0ms) + var(--i) * 110ms + var(--j) * 60ms)")
        }
        ".$visible .$content > :not(.$group), .$visible .$group > *" style {
            animation(revealUp) {
                duration(1.s)
                timingFunction(easeOutExpo)
                fillMode(AnimationFillMode.Both)
            }
            property("animation-delay", "var(--delay)")
        }
        ".$logoAnimated svg" style {
            display(DisplayStyle.Block)
            width(100.percent)
            property("height", "auto")
        }
        ".$visible .$content > .$logoAnimated" style {
            opacity(1)
            property("animation", "none")
        }
        ".$visible .$logoIntro .$logoStroke" style {
            opacity(1)
            property("stroke-dasharray", "1 2")
            // 200 ms dots fade in + 500 ms stretch
            animation(logoDots) {
                duration(.7.s)
                timingFunction(AnimationTimingFunction.cubicBezier(0.65, 0.0, 0.35, 1.0))
                fillMode(AnimationFillMode.Both)
            }
            property("animation-delay", "calc(var(--s) * 30ms)")
        }
        // The rest of the intro waits for the logo
        ".$visible .$logoLoop .$logoStroke" style {
            opacity(1)
            animation(logoPulse) {
                duration(1.6.s)
                timingFunction(AnimationTimingFunction.cubicBezier(0.65, 0.0, 0.35, 1.0))
                iterationCount(null)
                direction(AnimationDirection.Alternate)
                fillMode(AnimationFillMode.Both)
            }
            property("animation-delay", "calc(var(--s) * 60ms)")
        }
        ".$section:has(.$logoIntro) > .$half" style {
            property("--base", "380ms")
        }
        ".$visible:has(.$logoIntro) .$fullImage.$loaded" style {
            property("animation-delay", "650ms")
        }
        ".$visible .$photoButton" style {
            animation(fadeIn) {
                duration(.8.s)
                timingFunction(easeOutExpo)
                fillMode(AnimationFillMode.Both)
            }
            property("animation-delay", "1.4s")
            opacity(1)
        }
        ".$visible .$fullImage.$loaded" style {
            // Same as the last keyframe, so nothing changes when the animation ends
            opacity(1)
            property("transform", "scale(1)")
            animation(photoFade) {
                duration(1.6.s)
                timingFunction(easeOutExpo)
                fillMode(AnimationFillMode.Both)
            }
        }

        // Screenshots fanned like cards, spread a bit more on hover
        media(DESKTOP) {
            ".$onlyMobile" style {
                display(DisplayStyle.None)
            }
            ".$screenshot:nth-child(1)" style {
                property("rotate", "-7deg")
                property("translate", "28% 4%")
            }
            ".$screenshot:nth-child(2)" style {
                property("z-index", "1")
            }
            ".$screenshot:nth-child(3)" style {
                property("rotate", "7deg")
                property("translate", "-28% 4%")
            }
            ".$screenshots:hover .$screenshot:nth-child(1)" style {
                property("rotate", "-10deg")
                property("translate", "12% 6%")
            }
            ".$screenshots:hover .$screenshot:nth-child(2)" style {
                property("translate", "0 -3%")
            }
            ".$screenshots:hover .$screenshot:nth-child(3)" style {
                property("rotate", "10deg")
                property("translate", "-12% 6%")
            }
        }

        media(MOBILE) {
            // Section by section scrolling only on phones
            "html" style {
                property("scroll-snap-type", "y mandatory")
            }
            ".$section" style {
                property("scroll-snap-align", "start")
            }
            ".$screenshots" style {
                justifyContent(JustifyContent.FlexStart)
                property("height", "340px")
                property("overflow-x", "auto")
                property("scroll-snap-type", "x mandatory")
                gap(14.px)
                property("margin", "0 -24px")
                property("padding", "8px 24px 24px")
                property("scrollbar-width", "none")
            }
            ".$screenshot" style {
                property("scroll-snap-align", "center")
                property("box-shadow", "0 10px 24px rgba(0, 0, 0, .22)")
            }
            ".$modal" style {
                padding(24.px)
                property("align-self", "flex-end")
                property("max-height", "calc(100svh - 32px)")
            }
            ".$modalBackdrop" style {
                padding(16.px)
            }
            ".$serviceColumns" style {
                property("grid-template-columns", "1fr")
                gap(40.px)
            }
            ".$introActions" style {
                justifyContent(JustifyContent.SpaceBetween)
                property("padding", "20px 0")
            }
            ".$hideOnMobile" style {
                display(DisplayStyle.None)
            }
            ".$section" style {
                flexDirection(FlexDirection.Column)
                property("height", "auto")
                property("min-height", "100svh")
            }
            ".$half" style {
                width(100.percent)
                property("height", "auto")
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
            }
            ".$half:first-child" style {
                flexGrow(1)
            }
            ".$content" style {
                property("height", "auto")
                flexGrow(1)
                padding(40.px, 24.px)
            }
            ".$half + .$half .$content" style {
                property("padding-top", "0")
            }
            ".$spacer" style {
                property("min-height", "24px")
            }

            // Intro: photo fills the top of the screen, texts overlap its faded bottom
            ".$sectionOverlay" style {
                position(Position.Relative)
                property("height", "100svh")
            }
            ".$sectionOverlay > .$half:last-child" style {
                position(Position.Absolute)
                property("inset", "0 0 auto 0")
                property("height", "68svh")
            }
            ".$sectionOverlay > .$half:first-child" style {
                property("z-index", "1")
            }
            ".$sectionOverlay .$content" style {
                justifyContent(JustifyContent.FlexEnd)
                property("padding-bottom", "16px")
            }
            ".$sectionOverlay .$spacer" style {
                flexGrow(0)
                property("min-height", "20px")
            }
            ".$sectionOverlay .$logo" style {
                width(28.percent)
            }
            ".$photoFrame::after" style {
                property("content", "''")
                position(Position.Absolute)
                property("inset", "auto 0 0 0")
                property("height", "55%")
                property("background", "linear-gradient(to bottom, rgba(255, 255, 255, 0), #fff 85%)")
            }

            ".$title" style { property("font-size", "clamp(36px, 11vw, 64px)") }
            ".$subtitle" style { property("font-size", "clamp(26px, 7.5vw, 44px)") }
            ".$body" style { property("font-size", "clamp(17px, 4.8vw, 26px)") }
            ".$bodySmall" style { property("font-size", "clamp(14px, 4vw, 21px)") }
            ".$overline" style { property("font-size", "clamp(15px, 4.4vw, 24px)") }
            ".$icon" style { width(28.px) }
            ".$contactIcon" style {
                width(40.px)
                height(40.px)
            }
            ".$contactIcon img" style {
                width(19.px)
                height(19.px)
            }
            ".$dots" style {
                right(10.px)
                gap(10.px)
            }
            ".$dot" style { width(8.px) }
            ".$languageSwitch" style {
                top(14.px)
                right(10.px)
            }
        }
    }
}
