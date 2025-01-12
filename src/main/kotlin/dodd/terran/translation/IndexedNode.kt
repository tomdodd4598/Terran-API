package dodd.terran.translation

import java.util.*

abstract class IndexedNode(val value: MutableList<Node>) : Node() {

    val size get() = value.size

    inline operator fun <reified T: Node> get(index: Int): T? {
        val node = value[index]
        return if (node is T) node else null
    }

    fun indexOf(predicate: (Node) -> Boolean) = value.indexOfFirst(predicate)

    fun firstOf(predicate: (Node) -> Boolean) = value.firstOrNull(predicate)

    fun add(node: Node) {
        value.add(node)
    }

    fun addIfAbsent(node: Node, match: (Node) -> Boolean): Boolean {
        return if (value.none(match)) {
            add(node)
            true
        } else {
            false
        }
    }

    fun addRaw(line: String) {
        value.add(RawNode(line))
    }

    override fun shallowString() = "${javaClass.simpleName}${value.map { it.shallowStringInternal() }}"

    override fun shallowStringInternal() = "${javaClass.simpleName}[...]"

    override fun addToString(fb: FancyBuilder, indent: Boolean) {
        fb.append("${javaClass.simpleName}[\n", indent)

        val inner = fb.inner()
        for (e in value) {
            e.addToString(inner, true)
            fb.append("\n", false)
        }

        fb.append("]", true)
    }

    override fun equals(other: Any?) = other is IndexedNode && value == other.value

    override fun hashCode() = value.hashCode()

    override fun toString(): String {
        val fb = FancyBuilder()
        addToString(fb, false)
        return fb.toString()
    }
}

class ArrayNode(value: MutableList<Node>) : IndexedNode(value) {

    constructor(vararg nodes: Node) : this(mutableListOf(*nodes))

    override fun write(db: DefinitionBuilder, name: String) {
        db.append("$name - Size Int ${value.size}")

        val nodeName = "$name - Element"
        for (node in value) {
            node.write(db, nodeName)
        }
    }
}

class FlatNode(value: MutableList<Node>) : IndexedNode(value) {

    constructor(vararg nodes: Node) : this(mutableListOf(*nodes))

    override fun write(db: DefinitionBuilder, name: String) {
        for (node in value) {
            node.write(db, name)
        }
    }
}

abstract class ListNode(value: MutableList<Node>, private val intName: String, private val nodeName: String) : IndexedNode(value) {

    override fun write(db: DefinitionBuilder, name: String) {
        db.append("$intName Int ${value.size}")

        for (node in value) {
            node.write(db, nodeName)
        }
    }

    override fun equals(other: Any?) = other is ListNode && intName == other.intName && nodeName == other.nodeName && value == other.value

    override fun hashCode() = Objects.hash(value, intName, nodeName)
}

class PlayerListNode(value: MutableList<Node>) : ListNode(value, "PlayerList", "Player") {

    constructor(vararg nodes: Node) : this(mutableListOf(*nodes))
}

class WorldObjectsListNode(value: MutableList<Node>) : ListNode(value, "WorldObjects", "WorldObject") {

    constructor(vararg nodes: Node) : this(mutableListOf(*nodes))
}

class PointsListNode(value: MutableList<Node>) : ListNode(value, "Points", "Points") {

    constructor(vararg nodes: Node) : this(mutableListOf(*nodes))
}

class GroupsListNode(value: MutableList<Node>) : ListNode(value, "Num Groups", "Group") {

    constructor(vararg nodes: Node) : this(mutableListOf(*nodes))
}

class RuleListNode(value: MutableList<Node>) : ListNode(value, "Rule List", "Rule") {

    constructor(vararg nodes: Node) : this(mutableListOf(*nodes))
}

class ConditionListNode(value: MutableList<Node>) : ListNode(value, "NumConditions", "Condition List") {

    constructor(vararg nodes: Node) : this(mutableListOf(*nodes))
}

class ActionListNode(value: MutableList<Node>) : ListNode(value, "NumActions", "Action List") {

    constructor(vararg nodes: Node) : this(mutableListOf(*nodes))
}
