package weissar.web.theme

import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.AlignSelf
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
    private const val REDUCED_MOTION = "(prefers-reduced-motion: reduce)"
    private val easeOutExpo = AnimationTimingFunction.cubicBezier(0.16, 1.0, 0.3, 1.0)

    init {
        "*" style {
            margin(0.px)
            padding(0.px)
            boxSizing("border-box")
            fontFamily("Lexend", "sans-serif")
        }
        "html" style {
            property("scroll-behavior", "smooth")
            property("scroll-snap-type", "y mandatory")
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

    private val photoReveal by keyframes {
        from {
            property("clip-path", "inset(0 0 0 100%)")
            property("transform", "scale(1.15)")
        }
        to {
            property("clip-path", "inset(0 0 0 0)")
            property("transform", "scale(1)")
        }
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
        property("scroll-snap-align", "start")
    }

    /** On mobile the right half lies under the left one as a background. */
    val sectionOverlay by style {}

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
        property("clip-path", "inset(0 0 0 100%)")
        // Keeps the photo on its own GPU layer, otherwise it's repainted (blinks) when the reveal ends
        property("will-change", "transform, clip-path")
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

    val dotActive by style {
        height(32.px)
        opacity(1)
    }

    // endregion

    init {
        // Hover effects
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
        ".$visible .$fullImage.$loaded" style {
            // Same as the last keyframe, so nothing changes when the animation ends
            property("clip-path", "inset(0 0 0 0)")
            property("transform", "scale(1)")
            animation(photoReveal) {
                duration(1.6.s)
                timingFunction(easeOutExpo)
                fillMode(AnimationFillMode.Both)
            }
        }

        media(MOBILE) {
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
            ".$dots" style {
                right(10.px)
                gap(10.px)
            }
            ".$dot" style { width(8.px) }
        }

        media(REDUCED_MOTION) {
            "*" style {
                property("animation", "none !important")
                property("transition", "none !important")
                property("scroll-behavior", "auto !important")
            }
            ".$content > *, .$group > *" style {
                property("opacity", "1 !important")
            }
            ".$fullImage" style {
                property("clip-path", "none")
            }
        }
    }
}
