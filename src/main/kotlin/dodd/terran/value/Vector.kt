package dodd.terran.value

import dodd.terran.util.clean
import java.util.*
import kotlin.math.sqrt

class Vector(var x: Float, var y: Float, var z: Float = 0f) {

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

    init {
        x = x.clean()
        y = y.clean()
        z = z.clean()
    }

    fun norm() = sqrt(x * x + y * y + z * z)

    fun normalized(): Vector {
        val norm = norm()
        return if (norm == 0f) {
            zero
        } else {
            this / norm
        }
    }

    fun cross(other: Vector) = Vector(
        y * other.z - z * other.y,
        z * other.x - x * other.z,
        x * other.y - y * other.x
    )

    operator fun get(index: Int) = when (index) {
        0 -> x
        1 -> y
        else -> z
    }

    operator fun set(index: Int, value: Float) = when (index) {
        0 -> x = value
        1 -> y = value
        else -> z = value
    }

    operator fun unaryPlus() = Vector(x, y, z)

    operator fun unaryMinus() = Vector(-x, -y, -z)

    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Vector) = Vector(x - other.x, y - other.y, z - other.z)

    operator fun times(scalar: Float) = Vector(x * scalar, y * scalar, z * scalar)

    operator fun div(scalar: Float) = Vector(x / scalar, y / scalar, z / scalar)

    operator fun plusAssign(other: Vector) {
        x += other.x
        y += other.y
        z += other.z
    }

    operator fun minusAssign(other: Vector) {
        x -= other.x
        y -= other.y
        z -= other.z
    }

    operator fun timesAssign(scalar: Float) {
        x *= scalar
        y *= scalar
        z *= scalar
    }

    operator fun timesAssign(matrix: Matrix) {
        val result = matrix * this
        x = result.x
        y = result.y
        z = result.z
    }

    operator fun divAssign(scalar: Float) {
        x /= scalar
        y /= scalar
        z /= scalar
    }

    fun toCoord() = Coord(x, y)

    fun asSequence() = sequenceOf(x, y, z)

    override fun equals(other: Any?) = other is Vector && x == other.x && y == other.y && z == other.z

    override fun hashCode() = Objects.hash(x, y, z)

    override fun toString() = "[${x}f, ${y}f, ${z}f]"
}
