package dodd.terran.util

import dodd.terran.Global
import dodd.terran.translation.*
import dodd.terran.value.*
import dodd.terran.value.Vector
import java.io.File
import java.util.*
import kotlin.collections.ArrayDeque

object Helpers {
    fun <T> ArrayDeque<T>.push(elem: T) = addLast(elem)

    fun <T> ArrayDeque<T>.pop() = removeLast()

    fun <T> ArrayDeque<T>.peek() = last()

    fun Sequence<String>.filterNotEmpty() = filter { it.isNotEmpty() }

    fun Sequence<Float>.definitionString(type: String) = joinToString(", ", "$type( ", " )") { it.definitionString() }

    fun File.child(path: String) = File("$absolutePath/$path")

    fun String.splitByWhitespace() = split(Global.whitespaceRegex)

    fun String.capitalize() = replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

    private fun Float.places(n: Int) = "%.${n}f".format(this)

    fun Float.definitionString() = places(6)

    private fun Double.places(n: Int) = "%.${n}f".format(this)

    fun Double.definitionString() = places(6)

    val String.node get() = StringNode(this)

    val Boolean.node get() = BoolNode(this)

    val Int.node get() = IntNode(this)

    val Float.node get() = FloatNode(this)

    val Double.node get() = DoubleNode(this)

    val Coord.node get() = CoordNode(this)

    val Vector.node get() = VectorNode(this)

    val Color.node get() = ColorNode(this)

    val Matrix.node get() = MatrixNode(this)

    val Faction.node get() = this.id.node

    val Formation.node get() = this.id.node
}
