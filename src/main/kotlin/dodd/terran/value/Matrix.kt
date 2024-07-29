package dodd.terran.value

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class Matrix(private val internal: Array<FloatArray>) {

    companion object {
        val IDENTITY = Matrix(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)

        fun rotationX(angle: Float) = Matrix(1f, 0f, 0f, 0f, cos(angle), -sin(angle), 0f, sin(angle), cos(angle))

        fun rotationY(angle: Float) = Matrix(cos(angle), 0f, sin(angle), 0f, 1f, 0f, -sin(angle), 0f, cos(angle))

        fun rotationZ(angle: Float) = Matrix(cos(angle), -sin(angle), 0f, sin(angle), cos(angle), 0f, 0f, 0f, 1f)

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
                z * x * d - y * s, z * y * d + x * s, c + z * z * d,
            )
        }
    }

    constructor(x: FloatArray, y: FloatArray, z: FloatArray) : this(arrayOf(x, y, z))

    constructor(xx: Float, yx: Float, zx: Float, xy: Float, yy: Float, zy: Float, xz: Float, yz: Float, zz: Float) : this(floatArrayOf(xx, yx, zx), floatArrayOf(xy, yy, zy), floatArrayOf(xz, yz, zz))

    val rowCount = internal.size
    val colCount = internal.firstOrNull()?.size ?: 0

    operator fun get(rowIndex: Int, colIndex: Int) = internal[rowIndex][colIndex]

    operator fun set(rowIndex: Int, colIndex: Int, value: Float) {
        internal[rowIndex][colIndex] = value
    }

    operator fun unaryPlus() = Matrix(Array(rowCount) { i -> FloatArray(colCount) { j -> internal[i][j] } })

    operator fun unaryMinus() = Matrix(Array(rowCount) { i -> FloatArray(colCount) { j -> -internal[i][j] } })

    operator fun times(vector: Vector) = Vector(FloatArray(vector.size)).also {
        for (i in 0..<rowCount) {
            for (j in 0..<vector.size) {
                it[i] += internal[i][j] * vector[j]
            }
        }
    }

    operator fun times(other: Matrix) = Matrix(Array(rowCount) { FloatArray(other.colCount) }).also {
        for (i in 0..<rowCount) {
            for (j in 0..<other.colCount) {
                for (k in 0..<colCount) {
                    it.internal[i][j] += internal[i][k] * other.internal[k][j]
                }
            }
        }
    }

    operator fun timesAssign(other: Matrix) {
        val result = this * other
        for (i in 0..<rowCount) {
            for (j in 0..<colCount) {
                internal[i][j] = result.internal[i][j]
            }
        }
    }

    fun asSequence() = internal.asSequence()

    override operator fun equals(other: Any?) = other is Matrix && internal.contentDeepEquals(other.internal)

    override fun hashCode() = internal.contentDeepHashCode()

    override fun toString() = internal.contentDeepToString()
}
