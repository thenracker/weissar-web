package weissar.web.theme

import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.AlignSelf
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.alignSelf
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.boxSizing
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.flexGrow
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.css.vw
import org.jetbrains.compose.web.css.width

// Ratio: 80px from Figma = 4vw
object AppStyles : StyleSheet() {

    private val contentPadding = 5.vw

    init {
        "*" style {
            margin(0.px)
            padding(0.px)
            boxSizing("border-box")
            fontFamily("Lexend", "sans-serif")
        }
        "html, body" style {
            property("scroll-behavior", "smooth")
        }
        "body" style {
            property("overflow-y", "scroll")
        }
        "a" style {
            textDecoration("none")
        }
    }

    val section by style {
        display(DisplayStyle.Flex)
        width(100.percent)
        height(100.vh)
    }

    val half by style {
        width(50.percent)
        height(100.vh)
        position(Position.Relative)
    }

    val content by style {
        padding(contentPadding)
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        height(100.percent)
    }

    val fullImage by style {
        width(100.percent)
        height(100.percent)
        property("object-fit", "cover")
        position(Position.Absolute)
        bottom(0.px)
        right(0.px)
    }

    val logo by style {
        width(33.percent)
    }

    val end by style {
        alignSelf(AlignSelf.End)
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
    }

    val fillWidth by style {
        width(100.percent)
        padding(20.px)
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
    }

    val icon by style {
        width(32.px)
    }

    val spacer by style {
        flexGrow(1)
    }
}
