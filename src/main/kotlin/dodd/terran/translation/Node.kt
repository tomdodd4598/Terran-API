package dodd.terran.translation

abstract class Node {

    internal abstract fun write(db: DefinitionBuilder, name: String)

    open fun shallowString() = toString()

    internal open fun shallowStringInternal() = toString()

    internal open fun addToString(fb: FancyBuilder, indent: Boolean) = fb.append(toString(), indent)

    abstract override fun equals(other: Any?): Boolean

    abstract override fun hashCode(): Int

    abstract override fun toString(): String
}

class RawNode(var line: String) : Node() {

    override fun write(db: DefinitionBuilder, name: String) {
        db.append(line)
    }

    val entry get() = line to this

    override fun equals(other: Any?) = other is RawNode && line == other.line

    override fun hashCode() = line.hashCode()

    override fun toString() = line
}
