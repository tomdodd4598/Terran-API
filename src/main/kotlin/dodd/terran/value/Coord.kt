package dodd.terran.value

import dodd.terran.util.clean
import java.util.*

data class Coord(var u: Float, var v: Float) {

    init {
        u = u.clean()
        v = v.clean()
    }

    operator fun unaryPlus() = Coord(u, v)

    operator fun unaryMinus() = Coord(-u, -v)

    operator fun plus(other: Coord) = Coord(u + other.u, v + other.v)

    operator fun minus(other: Coord) = Coord(u - other.u, v - other.v)

    operator fun times(scalar: Float) = Coord(u * scalar, v * scalar)

    operator fun div(scalar: Float) = Coord(u / scalar, v / scalar)

    operator fun plusAssign(other: Coord) {
        u += other.u
        v += other.v
    }

    operator fun minusAssign(other: Coord) {
        u -= other.u
        v -= other.v
    }

    operator fun timesAssign(scalar: Float) {
        u *= scalar
        v *= scalar
    }

    operator fun divAssign(scalar: Float) {
        u /= scalar
        v /= scalar
    }

    fun toVector() = Vector(u, v)

    fun asSequence() = sequenceOf(u, v)

    override fun equals(other: Any?) = other is Coord && u == other.u && v == other.v

    override fun hashCode() = Objects.hash(u, v)

    override fun toString() = "Coord($u, $v)"
}
