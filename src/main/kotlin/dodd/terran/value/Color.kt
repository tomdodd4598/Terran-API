package dodd.terran.value

data class Color(var red: Float, var green: Float, var blue: Float, var alpha: Float = 1f) {

    companion object {
        val BLACK = Color(0f, 0f, 0f, 0f)
        val WHITE = Color(1f, 1f, 1f)
        val RED = Color(1f, 0f, 0f)
        val GREEN = Color(0f, 1f, 0f)
        val BLUE = Color(0f, 0f, 1f)
    }

    init {
        red = red.coerceIn(0f, 1f)
        green = green.coerceIn(0f, 1f)
        blue = blue.coerceIn(0f, 1f)
        alpha = alpha.coerceIn(0f, 1f)
    }

    operator fun unaryPlus() = Color(red, green, blue, alpha)

    operator fun plus(other: Color): Color {
        val a = 1f - (1f - alpha) * (1f - other.alpha)
        fun blend(x: Float, y: Float) = x * alpha / a + y * other.alpha * (1f - alpha) / a
        return Color(blend(red, other.red), blend(green, other.green), blend(blue, other.blue), a)
    }

    operator fun times(multiplier: Float) = Color(multiplier * red, multiplier * green, multiplier * blue, alpha)

    operator fun plusAssign(other: Color) {
        val result = this + other
        red = result.red
        green = result.green
        blue = result.blue
        alpha = result.alpha
    }

    operator fun timesAssign(multiplier: Float) {
        red *= multiplier
        green *= multiplier
        blue *= multiplier
    }

    fun asSequence() = sequenceOf(red, green, blue, alpha)
}
