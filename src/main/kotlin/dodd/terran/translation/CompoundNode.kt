package dodd.terran.translation

import dodd.terran.util.ListMap
import kotlin.math.max

abstract class CompoundNode(vararg pairs: Pair<String, Node>) : Node() {

    val internal = ListMap<String, Node>()

    val size get() = internal.size

    init {
        for ((x, y) in pairs) {
            internal[x] = y
        }
    }

    inline operator fun <reified T: Node> get(key: String): T? {
        val value = internal[key]
        return if (value is T) value else null
    }

    open operator fun set(key: String, value: Node) {
        internal[key] = value
    }

    fun setIfAbsent(key: String, value: Node): Boolean {
        return if (internal[key] == null) {
            internal[key] = value
            true
        } else {
            false
        }
    }

    fun addRaw(line: String) {
        this[line] = RawNode(line)
    }

    override fun shallowString() = "${javaClass.simpleName}${internal.asSequence().map { (x, y) -> if (y is RawNode) y else "\"$x\" = ${y.shallowStringInternal()}" }.toList()}"

    override fun shallowStringInternal() = "${javaClass.simpleName}[...]"

    override fun addToString(fb: FancyBuilder, indent: Boolean) {
        fb.append("${javaClass.simpleName}[\n", indent)

        val inner = fb.inner()
        for ((x, y) in internal) {
            val raw = y is RawNode
            if (!raw) {
                inner.append("\"$x\" = ", true)
            }
            y.addToString(inner, raw)
            fb.append("\n", false)
        }

        fb.append("]", true)
    }

    override fun equals(other: Any?) = other is CompoundNode && internal == other.internal

    override fun hashCode() = internal.hashCode()

    override fun toString(): String {
        val fb = FancyBuilder()
        addToString(fb, false)
        return fb.toString()
    }
}

class TupleNode(vararg pairs: Pair<String, Node>) : CompoundNode(*pairs) {

    override fun write(db: DefinitionBuilder, name: String) {
        for ((key, value) in internal) {
            value.write(db, key)
        }
    }
}

open class NestedNode(vararg pairs: Pair<String, Node>) : CompoundNode(*pairs), StackElement {

    private var rawPrevious: RawNode? = null

    open fun innerKey(key: String) = key

    override fun write(db: DefinitionBuilder, name: String) {
        val inner = db.inner()
        for ((key, value) in internal) {
            value.write(inner, innerKey(key))
        }
        val lines = inner.lines()

        val size = max(0, lines.size - 1)
        db.append("${size.toString().padStart(8, '0')} $name")
        db.append("{")
        lines.asSequence().take(size).forEach { db.append(it) }
        db.append("}")
    }

    override fun set(key: String, value: Node) {
        super.set(key, value)
        rawPrevious = if (value is RawNode) value else null
    }

    override fun rawPrevious() = rawPrevious

    override fun tryNext(lineIter: Iterator<String>, start: Boolean) = lineIter.next() to true
}

class RootNode : NestedNode() {

    override fun write(db: DefinitionBuilder, name: String) {
        for ((key, value) in internal) {
            value.write(db, key)
        }
    }

    fun build(): DefinitionBuilder {
        val db = DefinitionBuilder(false)
        write(db, "root")
        return db
    }
}

class ConditionNode(vararg pairs: Pair<String, Node>) : NestedNode(*pairs)

class ActionNode(vararg pairs: Pair<String, Node>) : NestedNode(*pairs)

class MixNode(vararg pairs: Pair<String, Node>) : NestedNode(*pairs) {

    private var index = 0

    override fun set(key: String, value: Node) {
        super.set("$index $key", value)
        ++index
    }

    override fun innerKey(key: String) = key.split(" ", limit = 2)[1]
}
