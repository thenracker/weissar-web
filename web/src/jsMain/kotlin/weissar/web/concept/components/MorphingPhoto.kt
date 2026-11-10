package weissar.web.concept.components

import androidx.compose.runtime.Composable
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.Element
import weissar.web.concept.theme.AppStyles
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Photo clipped by Material 3 Expressive shapes (MaterialShapes), morphing to the next one every second,
 * shrinking a bit during the morph and rotating clockwise. The photo itself stays upright.
 */
@Composable
fun MorphingPhoto(src: String, alt: String) {
    Div({
        classes(AppStyles.morphingPhoto)
        ref { element ->
            val morph = PhotoMorph(element, src, alt)
            onDispose { morph.stop() }
        }
    })
}

private const val POINTS = 180
private const val SVG_NS = "http://www.w3.org/2000/svg"

private const val HOLD_MS = 800.0
private const val MORPH_MS = 560.0
private const val SPIN_DEG_PER_MS = 360.0 / 20_000
private const val KICK_DEG = 40.0
private const val SHRINK = .07

/** Shape as radius per angle, all shapes have the same [POINTS] so any two can be blended. */
private class MaterialShape(val name: String, val radii: DoubleArray)

private val shapes = listOf(
    lobes("Cookie4Sided", 4, depth = .12, sharpness = 1.0),
    lobes("Clover4Leaf", 4, depth = .32, sharpness = .45),
    rounded("Pentagon", polygon(5), smoothing = 14),
    lobes("Boom", 15, depth = .24, sharpness = 3.5),
    stretched("Oval", ellipse(.7)),
    lobes("Cookie7Sided", 7, depth = .1, sharpness = 1.0),
    rounded("Triangle", polygon(3), smoothing = 22),
    lobes("SoftBurst", 10, depth = .14, sharpness = 2.2),
    rounded("Gem", polygon(6), smoothing = 12),
    lobes("VerySunny", 8, depth = .2, sharpness = 3.0),
    stretched("Pill", rounded("", rectangle(.6), smoothing = 20).radii),
    lobes("Cookie9Sided", 9, depth = .08, sharpness = 1.0),
    rounded("Square", polygon(4), smoothing = 22),
    lobes("Burst", 12, depth = .2, sharpness = 3.0),
    lobes("Sunny", 8, depth = .1, sharpness = 2.6),
    lobes("Clover8Leaf", 8, depth = .18, sharpness = .45),
    lobes("Cookie6Sided", 6, depth = .1, sharpness = 1.0),
    lobes("SoftBoom", 16, depth = .12, sharpness = 1.6),
    lobes("Puffy", 6, depth = .16, sharpness = .35),
    lobes("Cookie12Sided", 12, depth = .06, sharpness = 1.0),
    lobes("Circle", 1, depth = .0, sharpness = 1.0),
)

/** [sharpness] above 1 makes pointy tips (sunny, burst), below 1 round lobes with narrow gaps (clover). */
private fun lobes(name: String, count: Int, depth: Double, sharpness: Double) = MaterialShape(
    name,
    normalized(DoubleArray(POINTS) { i ->
        val wave = (1 + cos(count * angle(i))) / 2
        1 - depth * (1 - wave.pow(sharpness))
    }),
)

/** Radius of a regular polygon with a vertex on top. */
private fun polygon(sides: Int) = DoubleArray(POINTS) { i ->
    val sector = 2 * PI / sides
    val local = angle(i) - sector * floor(angle(i) / sector) - sector / 2
    cos(sector / 2) / cos(local)
}

/** Ellipse [ratio] times as wide as tall. */
private fun ellipse(ratio: Double) = DoubleArray(POINTS) { i ->
    val a = angle(i)
    1 / sqrt((sin(a) / ratio).pow(2) + cos(a).pow(2))
}

/** Rectangle [ratio] times as wide as tall. */
private fun rectangle(ratio: Double) = DoubleArray(POINTS) { i ->
    val a = angle(i)
    min(ratio / abs(sin(a)), 1 / abs(cos(a)))
}

private fun stretched(name: String, radii: DoubleArray) = MaterialShape(name, normalized(radii))

/** Rounds the corners by averaging neighbouring radii. */
private fun rounded(name: String, radii: DoubleArray, smoothing: Int) = MaterialShape(
    name,
    normalized(DoubleArray(POINTS) { i ->
        (-smoothing..smoothing).sumOf { radii[(i + it + POINTS) % POINTS] } / (2 * smoothing + 1)
    }),
)

private fun normalized(radii: DoubleArray): DoubleArray {
    val largest = radii.max()
    return DoubleArray(POINTS) { radii[it] / largest }
}

private fun angle(i: Int) = 2 * PI * i / POINTS

/** Material "emphasized" like easing with a small overshoot, so the morph feels springy. */
private fun spring(t: Double): Double {
    val c = 1.6
    val x = t - 1
    return 1 + (c + 1) * x * x * x + c * x * x
}

private fun easeInOut(t: Double) = if (t < .5) 4 * t * t * t else 1 - (-2 * t + 2).pow(3) / 2

private class PhotoMorph(
    container: Element,
    src: String,
    alt: String,
) {
    private val clipPath = svg("path")
    private val outline = svg("path")
    private var frame = 0
    private var stopped = false

    private var from = shapes.first()
    private var index = 0
    private var cycleStart = 0.0
    private var kick = 0.0
    private val reducedMotion = window.matchMedia("(prefers-reduced-motion: reduce)").matches

    init {
        val root = svg("svg").apply {
            setAttribute("viewBox", "0 0 1 1")
            setAttribute("role", "img")
            setAttribute("aria-label", alt)
        }
        val clip = svg("clipPath").apply {
            setAttribute("id", "photo-shape")
            appendChild(clipPath)
        }
        val image = svg("image").apply {
            setAttribute("href", src)
            setAttribute("width", "1")
            setAttribute("height", "1")
            setAttribute("preserveAspectRatio", "xMidYMid slice")
            setAttribute("clip-path", "url(#photo-shape)")
        }
        outline.apply {
            setAttribute("fill", "none")
            setAttribute("class", AppStyles.photoOutline)
            setAttribute("vector-effect", "non-scaling-stroke")
        }
        root.appendChild(svg("defs").apply { appendChild(clip) })
        root.appendChild(image)
        root.appendChild(outline)
        container.appendChild(root)

        if (reducedMotion) draw(shapes[index].radii, shapes[index].radii, 1.0, 1.0, 0.0) else frame = window.requestAnimationFrame(::tick)
    }

    fun stop() {
        stopped = true
        window.cancelAnimationFrame(frame)
    }

    private fun tick(now: Double) {
        if (stopped) return
        if (cycleStart == 0.0) cycleStart = now

        if (now - cycleStart >= HOLD_MS + MORPH_MS) {
            from = shapes[index]
            index = (index + 1) % shapes.size
            cycleStart = now
            kick += KICK_DEG
        }

        // Holds the shape for a second, then morphs into the next one
        val progress = ((now - cycleStart - HOLD_MS) / MORPH_MS).coerceIn(0.0, 1.0)
        val scale = 1 - SHRINK * sin(PI * easeInOut(progress))
        val rotation = now * SPIN_DEG_PER_MS + kick + KICK_DEG * spring(progress)
        draw(from.radii, shapes[index].radii, spring(progress), scale, rotation)

        frame = window.requestAnimationFrame(::tick)
    }

    private fun draw(from: DoubleArray, to: DoubleArray, t: Double, scale: Double, rotationDeg: Double) {
        val rotation = rotationDeg * PI / 180
        val d = buildString {
            for (i in 0 until POINTS) {
                val r = max(0.0, from[i] + (to[i] - from[i]) * t) * scale * .5
                val a = angle(i) + rotation
                append(if (i == 0) 'M' else 'L')
                append(format(.5 + r * sin(a)))
                append(' ')
                append(format(.5 - r * cos(a)))
            }
            append('Z')
        }
        clipPath.setAttribute("d", d)
        outline.setAttribute("d", d)
    }

    private fun format(value: Double) = (floor(value * 10_000) / 10_000).toString()

    private fun svg(name: String) = document.createElementNS(SVG_NS, name)
}
