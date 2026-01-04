package dodd.terran.value

import dodd.terran.util.clean
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class Matrix(var xx: Float, var yx: Float, var zx: Float, var xy: Float, var yy: Float, var zy: Float, var xz: Float, var yz: Float, var zz: Float) {

    companion object {
        val identity get() = Matrix(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)

        fun rotationX(angle: Float) = rotationX(cos(angle), -sin(angle))

        fun rotationX(yy: Float, zy: Float) = Matrix(1f, 0f, 0f, 0f, yy, zy, 0f, -zy, yy)

        fun rotationY(angle: Float) = rotationY(cos(angle), sin(angle))

        fun rotationY(xx: Float, zx: Float) = Matrix(xx, 0f, zx, 0f, 1f, 0f, -zx, 0f, xx)

        fun rotationZ(angle: Float) = rotationZ(cos(angle), -sin(angle))

        fun rotationZ(xx: Float, yx: Float) = Matrix(xx, yx, 0f, -yx, xx, 0f, 0f, 0f, 1f)

        fun rotation(angle: Float, axis: Vector): Matrix {
            val n = axis.normalized()
            val x = n.x
            val y = n.y
            val z = n.z
            val c = cos(angle)
            val d = 1f - c
            val s = sin(angle)
            return Matrix(
                c + x * x * d, x * y * d - z * s, x * z * d + y * s,
                y * x * d + z * s, c + y * y * d, y * z * d - x * s,
                z * x * d - y * s, z * y * d + x * s, c + z * z * d
            )
        }
    }

    init {
        xx = xx.clean()
        yx = yx.clean()
        zx = zx.clean()
        xy = xy.clean()
        yy = yy.clean()
        zy = zy.clean()
        xz = xz.clean()
        yz = yz.clean()
        zz = zz.clean()
    }

    val transpose get() = Matrix(xx, xy, xz, yx, yy, yz, zx, zy, zz)

    operator fun get(rowIndex: Int, colIndex: Int) = when (rowIndex) {
        0 -> when (colIndex) {
            0 -> xx
            1 -> yx
            else -> zx
        }
        1 -> when (colIndex) {
            0 -> xy
            1 -> yy
            else -> zy
        }
        else -> when (colIndex) {
            0 -> xz
            1 -> yz
            else -> zz
        }
    }

    operator fun set(rowIndex: Int, colIndex: Int, value: Float) = when (rowIndex) {
        0 -> when (colIndex) {
            0 -> xx = value
            1 -> yx = value
            else -> zx = value
        }
        1 -> when (colIndex) {
            0 -> xy = value
            1 -> yy = value
            else -> zy = value
        }
        else -> when (colIndex) {
            0 -> xz = value
            1 -> yz = value
            else -> zz = value
        }
    }

    operator fun unaryPlus() = Matrix(xx, yx, zx, xy, yy, zy, xz, yz, zz)

    operator fun unaryMinus() = Matrix(-xx, -yx, -zx, -xy, -yy, -zy, -xz, -yz, -zz)

    operator fun times(vector: Vector) = Vector(
        xx * vector.x + yx * vector.y + zx * vector.z,
        xy * vector.x + yy * vector.y + zy * vector.z,
        xz * vector.x + yz * vector.y + zz * vector.z
    )

    operator fun times(other: Matrix) = Matrix(
        xx * other.xx + yx * other.xy + zx * other.xz, xx * other.yx + yx * other.yy + zx * other.yz, xx * other.zx + yx * other.zy + zx * other.zz,
        xy * other.xx + yy * other.xy + zy * other.xz, xy * other.yx + yy * other.yy + zy * other.yz, xy * other.zx + yy * other.zy + zy * other.zz,
        xz * other.xx + yz * other.xy + zz * other.xz, xz * other.yx + yz * other.yy + zz * other.yz, xz * other.zx + yz * other.zy + zz * other.zz
    )

    operator fun timesAssign(other: Matrix) {
        val result = this * other
        xx = result.xx
        yx = result.yx
        zx = result.zx
        xy = result.xy
        yy = result.yy
        zy = result.zy
        xz = result.xz
        yz = result.yz
        zz = result.zz
    }

    fun asSequence() = sequenceOf(xx, yx, zx, xy, yy, zy, xz, yz, zz)

    override fun equals(other: Any?) = other is Matrix
            && xx == other.xx && yx == other.yx && zx == other.zx
            && xy == other.xy && yy == other.yy && zy == other.zy
            && xz == other.xz && yz == other.yz && zz == other.zz

    override fun hashCode() = Objects.hash(xx, yx, zx, xy, yy, zy, xz, yz, zz)

    override fun toString() = "[[${xx}f, ${yx}f, ${zx}f], [${xy}f, ${yy}f, ${zy}f], [${xz}f, ${yz}f, ${zz}f]]"
}
