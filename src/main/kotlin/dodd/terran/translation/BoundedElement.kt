package dodd.terran.translation

abstract class IndexedElement(private val size: Int) : StackElement {

    val internal = mutableListOf<Node>()
    private var rawPrevious: RawNode? = null

    override fun set(key: String, value: Node) {
        internal.add(value)
        rawPrevious = if (value is RawNode) value else null
    }

    override fun rawPrevious() = rawPrevious

    override fun tryNext(lineIter: Iterator<String>, start: Boolean): Pair<String, Boolean> {
        val inBounds = internal.size < size
        return (if (inBounds) nextLine(lineIter) else "") to inBounds
    }

    abstract fun nextLine(lineIter: Iterator<String>): String

    abstract fun node(): Node
}

class ArrayElement(size: Int) : IndexedElement(size) {

    override fun nextLine(lineIter: Iterator<String>) = lineIter.next().replace("- Element", "").trim()

    override fun node() = ArrayNode(internal)
}

abstract class ListElement(size: Int, private val constructor: (MutableList<Node>) -> IndexedNode) : IndexedElement(size) {

    override fun nextLine(lineIter: Iterator<String>) = lineIter.next()

    override fun node() = constructor(internal)
}

class FlatElement(size: Int) : ListElement(size, ::FlatNode)

class PlayerListElement(size: Int) : ListElement(size, ::PlayerListNode)

class WorldObjectsListElement(size: Int) : ListElement(size, ::WorldObjectsListNode)

class PointsListElement(size: Int) : ListElement(size, ::PointsListNode)

class GroupsListElement(size: Int) : ListElement(size, ::GroupsListNode)

class RuleListElement(size: Int) : ListElement(size, ::RuleListNode)

class ConditionListElement(size: Int) : ListElement(size, ::ConditionListNode)

class ActionListElement(size: Int) : ListElement(size, ::ActionListNode)
