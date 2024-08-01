package dodd.terran.value

import kotlin.math.sqrt

class Vector(private val internal: FloatArray) {

    companion object {
        val zero get() = Vector(0f, 0f)
        val east get() = Vector(1f, 0f)
        val west get() = Vector(-1f, 0f)
        val north get() = Vector(0f, 1f)
        val south get() = Vector(0f, -1f)
        val up get() = Vector(0f, 0f, 1f)
        val down get() = Vector(0f, 0f, -1f)

        fun dir(x: Float, y: Float, z: Float = 0f) = Vector(x, y, z).normalized()
    }

    constructor(x: Float, y: Float, z: Float = 0f) : this(floatArrayOf(x, y, z))

    val size get() = internal.size

    val x get() = this[0]

    val y get() = this[1]

    val z get() = this[2]

    fun norm(): Float {
        var square = 0f
        for (element in internal) {
            square += element * element
        }
        return sqrt(square)
    }

    fun normalized(): Vector {
        val norm = norm()
        return if (norm == 0f) {
            zero
        } else {
            this / norm
        }
    }

    fun cross(other: Vector) = Vector(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x)

    operator fun get(index: Int) = internal[index]

    operator fun set(index: Int, value: Float) {
        internal[index] = value
    }

    operator fun unaryPlus() = Vector(FloatArray(size) { internal[it] })

    operator fun unaryMinus() = Vector(FloatArray(size) { -internal[it] })

    operator fun plus(other: Vector) = Vector(FloatArray(size) { internal[it] + other.internal[it] })

    operator fun minus(other: Vector) = Vector(FloatArray(size) { internal[it] - other.internal[it] })

    operator fun times(scalar: Float) = Vector(FloatArray(size) { internal[it] * scalar })

    operator fun div(scalar: Float) = Vector(FloatArray(size) { internal[it] / scalar })

    operator fun plusAssign(other: Vector) {
        for (i in internal.indices) {
            internal[i] += other.internal[i]
        }
    }

    operator fun minusAssign(other: Vector) {
        for (i in internal.indices) {
            internal[i] -= other.internal[i]
        }
    }

    operator fun timesAssign(scalar: Float) {
        for (i in internal.indices) {
            internal[i] *= scalar
        }
    }

    operator fun timesAssign(matrix: Matrix) {
        val result = matrix * this
        for (i in internal.indices) {
            internal[i] = result.internal[i]
        }
    }

    operator fun divAssign(scalar: Float) {
        for (i in internal.indices) {
            internal[i] /= scalar
        }
    }

    fun asSequence() = internal.asSequence()

    override operator fun equals(other: Any?) = other is Vector && internal.contentEquals(other.internal)

    override fun hashCode() = internal.contentHashCode()

    override fun toString() = internal.joinToString(prefix = "[", postfix = "]") { "${it}f" }
}
