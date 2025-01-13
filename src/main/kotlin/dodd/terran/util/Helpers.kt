package dodd.terran.util

import dodd.terran.Global
import dodd.terran.World
import dodd.terran.translation.*
import dodd.terran.util.Helpers.node
import dodd.terran.value.*
import dodd.terran.value.Vector
import java.io.File
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.PI

object Helpers {
    fun <T> ArrayDeque<T>.push(elem: T) = addLast(elem)

    fun <T> ArrayDeque<T>.pop() = removeLast()

    fun <T> ArrayDeque<T>.peek() = last()

    fun Sequence<String>.filterNotEmpty() = filter { it.isNotEmpty() }

    fun Sequence<Float>.definitionString(type: String) = joinToString(", ", "$type( ", " )") { it.definitionString() }

    fun File.child(path: String) = File("$absolutePath/$path")

    fun String.splitByWhitespace() = split(Global.whitespaceRegex)

    fun String.capitalize() = replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

    fun String.titlecase() = lowercase().capitalize()

    private fun Float.places(n: Int) = "%.${n}f".format(this)

    fun Float.definitionString() = places(6)

    private fun Double.places(n: Int) = "%.${n}f".format(this)

    fun Double.definitionString() = places(6)

    fun Float.toRadians() = (PI * this / 180.0).toFloat()

    fun Float.clean() = if (this == -0f) 0f else this

    fun Double.clean() = if (this == -0.0) 0.0 else this

    fun <T> List<T>.forEachPair(function: (T, T) -> Unit) {
        for (i in indices) {
            for (j in (i + 1)..<size) {
                function(this[i], this[j])
            }
        }
    }

    val String.node get() = StringNode(this)

    val Boolean.node get() = BoolNode(this)

    val Int.node get() = IntNode(this)

    val Float.node get() = FloatNode(this)

    val Double.node get() = DoubleNode(this)

    val Coord.node get() = CoordNode(this)

    val Vector.node get() = VectorNode(this)

    val Color.node get() = ColorNode(this)

    val Matrix.node get() = MatrixNode(this)

    val Faction.node get() = ordinal.node

    val Formation.node get() = ordinal.node

    val HeadLocation.node get() = ordinal.node

    val Equivalence.node get() = string.node

    val FollowMode.node get() = string.node

    val Skill.node get() = string.node

    val Stance.node get() = string.node

    val VitalSection.node get() = string.node

    fun Boolean.stringNode() = toString().uppercase().node

    infix fun String.of(groupName: String) = "$groupName,$this"
}
