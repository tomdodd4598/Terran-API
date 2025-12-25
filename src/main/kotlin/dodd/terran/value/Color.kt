package dodd.terran.value

import dodd.terran.util.Helpers.clean

data class Color(var red: Float, var green: Float, var blue: Float, var alpha: Float = 1f) {

    companion object {
        val none get() = Color(0f, 0f, 0f, 0f)
        val black get() = Color(0f, 0f, 0f)
        val white get() = Color(1f, 1f, 1f)
        val red get() = Color(1f, 0f, 0f)
        val green get() = Color(0f, 1f, 0f)
        val blue get() = Color(0f, 0f, 1f)
        val yellow get() = Color(1f, 1f, 0f)
        val cyan get() = Color(0f, 1f, 1f)
        val magenta get() = Color(1f, 0f, 1f)
    }

    init {
        red = red.coerceIn(0f, 1f).clean()
        green = green.coerceIn(0f, 1f).clean()
        blue = blue.coerceIn(0f, 1f).clean()
        alpha = alpha.coerceIn(0f, 1f).clean()
    }

    operator fun unaryPlus() = Color(red, green, blue, alpha)

    operator fun unaryMinus() = Color(1f - red, 1f - green, 1f - blue, alpha)

    operator fun plus(other: Color): Color {
        val a = other.alpha + alpha * (1f - other.alpha)
        return if (a > 0f) {
            fun blend(x: Float, y: Float) = (y * other.alpha + x * alpha * (1f - other.alpha)) / a
            Color(blend(red, other.red), blend(green, other.green), blend(blue, other.blue), a)
        } else {
            none
        }
    }

    operator fun minus(other: Color) = this + (-other)

    operator fun times(scalar: Float) = Color(red * scalar, green * scalar, blue * scalar, alpha)

    operator fun div(scalar: Float) = Color(red / scalar, green / scalar, blue / scalar, alpha)

    operator fun plusAssign(other: Color) {
        val result = this + other
        red = result.red
        green = result.green
        blue = result.blue
        alpha = result.alpha
    }

    operator fun minusAssign(other: Color) {
        val result = this - other
        red = result.red
        green = result.green
        blue = result.blue
        alpha = result.alpha
    }

    operator fun timesAssign(scalar: Float) {
        val result = this * scalar
        red = result.red
        green = result.green
        blue = result.blue
        alpha = result.alpha
    }

    operator fun divAssign(scalar: Float) {
        val result = this / scalar
        red = result.red
        green = result.green
        blue = result.blue
        alpha = result.alpha
    }

    fun asSequence() = sequenceOf(red, green, blue, alpha)

    override fun toString() = "Color($red, $green, $blue, $alpha)"
}
