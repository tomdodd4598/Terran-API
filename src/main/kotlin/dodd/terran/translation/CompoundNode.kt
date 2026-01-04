package dodd.terran.translation

import dodd.terran.util.ListMap
import kotlin.math.max

abstract class CompoundNode(list: List<Pair<String, Node>>) : Node() {

    val internal = ListMap(list)

    val size get() = internal.size

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

class TupleNode(list: List<Pair<String, Node>>) : CompoundNode(list) {

    constructor(vararg pairs: Pair<String, Node>) : this(listOf(*pairs))

    override fun write(db: DefinitionBuilder, name: String) {
        for ((key, value) in internal) {
            value.write(db, key)
        }
    }
}

open class NestedNode(list: List<Pair<String, Node>>) : CompoundNode(list), StackElement {

    constructor(vararg pairs: Pair<String, Node>) : this(listOf(*pairs))

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
        lines.asSequence().take(size).forEach(db::append)
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

class ConditionNode(list: List<Pair<String, Node>>) : NestedNode(list) {

    constructor(vararg pairs: Pair<String, Node>) : this(listOf(*pairs))
}

class ActionNode(list: List<Pair<String, Node>>) : NestedNode(list) {

    constructor(vararg pairs: Pair<String, Node>) : this(listOf(*pairs))
}

class MixNode(list: List<Pair<String, Node>>) : NestedNode(list) {

    constructor(vararg pairs: Pair<String, Node>) : this(listOf(*pairs))

    private var index = 0

    override fun set(key: String, value: Node) {
        super.set("$index $key", value)
        ++index
    }

    override fun innerKey(key: String) = key.split(" ", limit = 2)[1]
}
