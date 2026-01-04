package dodd.terran.util

import dodd.terran.value.Matrix
import dodd.terran.value.Vector
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

fun Random.nextUnitVector(): Vector {
    val z = 2f * nextFloat() - 1f
    val r = sqrt(1f - z * z)
    val p = (2.0 * PI).toFloat() * nextFloat()
    return Vector(r * cos(p), r * sin(p), z)
}

fun Random.nextRotationMatrix(): Matrix {
    val a = nextFloat()
    val u = sqrt(a)
    val v = sqrt(1f - a)
    val t = (2.0 * PI).toFloat() * nextFloat()
    val p = (2.0 * PI).toFloat() * nextFloat()
    val w = u * cos(t)
    val x = v * sin(p)
    val y = v * cos(p)
    val z = u * sin(t)
    return Matrix(
        1f - 2f * (y * y + z * z), 2f * (x * y - w * z), 2f * (x * z + w * y),
        2f * (x * y + w * z), 1f - 2f * (x * x + z * z), 2f * (y * z - w * x),
        2f * (x * z - w * y), 2f * (y * z + w * x), 1f - 2f * (x * x + y * y)
    )
}
