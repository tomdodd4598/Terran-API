package dodd.terran.translation

import dodd.terran.util.*
import dodd.terran.value.Matrix
import dodd.terran.value.Color
import dodd.terran.value.Coord
import dodd.terran.value.Vector
import java.io.File

class DefinitionParser(private val lines: Iterable<String>) {

    constructor(file: File) : this(file.readLines())

    private val stack = ArrayDeque<StackElement>()

    fun result(): RootNode {
        val root = RootNode()
        stack.push(root)
        parse(ParsingIterator(lines.asSequence().map { it.trim() }.filterNotEmpty().iterator()))
        stack.pop()
        return root
    }

    private fun parse(iter: ParsingIterator) {
        val elem = stack.peek()

        fun nextLine() = iter.tryNext(elem, false).first

        fun nextLineLast() = nextLine().splitByWhitespace().last()

        fun trimSplit(line: String, delim: String) = line.splitToSequence(delim).map { it.trim() }.toList()

        fun stringValue(line: String) = line.split("'").let { it[it.size - 2] }

        fun boolNode(str: String) = str.toBoolean().node

        fun intNode(str: String) = str.toInt().node

        fun floatNode(str: String) = str.toFloat().node

        fun doubleNode(str: String) = str.toDouble().node

        fun nestedName(line: String, first: String?) = line.substring((first ?: line.splitByWhitespace().first()).length).trim()

        fun pushParsePop(innerElement: StackElement) {
            stack.push(innerElement)
            parse(iter)
            stack.pop()
        }

        fun parseNextNested(nested: NestedNode) {
            nextLine() // {
            pushParsePop(nested)
        }

        fun floatTupleSplit(line: String, type: String): Pair<String, Sequence<Float>> {
            val (name, str) = trimSplit(line, type)
            return name to str.split("(", ")")[1].splitToSequence(",").map { it.toFloat() }
        }

        while (iter.hasNext()) {
            val (line, valid) = iter.tryNext(elem, true)
            if (!valid || line == "}") {
                break
            }

            val splitWhitespace = line.splitByWhitespace()
            val firstWord = splitWhitespace.first()
            val lastWord = splitWhitespace.last()

            fun parseNextList(name: String, constructor: (Int) -> ListElement) {
                val listElem = constructor(lastWord.toInt())
                pushParsePop(listElem)
                elem[name] = listElem.node()
            }

            fun scalarName(type: String) = line.substring(0..<line.substring(0..<line.length - lastWord.length).lastIndexOf(type)).trim()

            // # ...
            if (line.startsWith("#")) {
                // Fudge part 1
                elem[line] = RawNode(line)
            }

            // Rule Name String '...'
            else if (elem is RuleListElement && line.startsWith("Rule Name String")) {
                val name = stringValue(line)
                val ruleNamePair = "Rule Name" to name.node
                val runOncePair = "Run Once" to boolNode(nextLineLast())
                val isActivePair = "Is Active" to boolNode(nextLineLast())

                val conditionList = ConditionListElement(nextLineLast().toInt())
                pushParsePop(conditionList)
                val conditionListPair = "Conditions" to conditionList.node()

                val actionList = ActionListElement(nextLineLast().toInt())
                pushParsePop(actionList)
                val actionListPair = "Actions" to actionList.node()

                elem[name] = TupleNode(ruleNamePair, runOncePair, isActivePair, conditionListPair, actionListPair)
            }

            // 0... Condition List
            else if (elem is ConditionListElement && line.contains("Condition List")) {
                val condition = ConditionNode()
                parseNextNested(condition)
                elem["Condition List"] = condition
            }

            // 0... Action List
            else if (elem is ActionListElement && line.contains("Action List")) {
                val action = ActionNode()
                parseNextNested(action)
                elem["Action List"] = action
            }

            // ID Int ...
            else if (elem is WorldObjectsListElement && line.startsWith("ID Int")) {
                val idPair = "ID" to intNode(lastWord)
                val typePair = "Type" to stringValue(nextLine()).node

                val nestName = nestedName(nextLine(), null)
                val nested = NestedNode()
                parseNextNested(nested)
                val nestedPair = nestName to nested

                elem["WorldObject"] = TupleNode(idPair, typePair, nestedPair)
            }

            // Type String '...'
            else if (elem !is ConditionNode && elem !is ActionNode && line.startsWith("Type String")) {
                val name = stringValue(line)
                val empty = name.isEmpty()
                val rawPrevious = elem.rawPrevious()
                val modifiedName = if (rawPrevious == null) name else "${rawPrevious}Type"
                if (empty && rawPrevious != null) {
                    // Fudge part 2
                    elem[modifiedName] = TupleNode("Type" to "".node)
                }
                else if (!empty) {
                    val typePair = "Type" to name.node

                    val nestName = nestedName(nextLine(), null)
                    val nested = NestedNode()
                    parseNextNested(nested)
                    val nestedPair = nestName to nested

                    elem[modifiedName] = TupleNode(typePair, nestedPair)
                }
            }

            // Definition String '...'
            else if (line.startsWith("Definition String")) {
                val name = stringValue(line)
                if (name.isNotEmpty()) {
                    val pairList = mutableListOf<Pair<String, Node>>()

                    pairList.add("Definition" to name.node)
                    val entity = stringValue(nextLine())
                    pairList.add("EntityType" to entity.node)
                    if (entity.isNotEmpty()) {
                        pairList.add("FactoryType" to stringValue(nextLine()).node)

                        val nestName = nestedName(nextLine(), null)
                        val nested = NestedNode()
                        parseNextNested(nested)
                        pairList.add(nestName to nested)
                    }

                    elem[name] = TupleNode(pairList)
                }
            }

            // ... - Size Int ...
            else if (line.contains("- Size Int")) {
                val (name, size) = trimSplit(line, "- Size Int")
                val arrayElem = ArrayElement(size.toInt())
                pushParsePop(arrayElem)
                elem[name] = arrayElem.node()
            }

            // Number of Players Int ...
            else if (line.contains("Number of Players Int")) {
                val size = lastWord.toInt()
                elem["Number of Players"] = size.node
                val flatElem = FlatElement(size)
                pushParsePop(flatElem)
                elem["Players"] = flatElem.node()
            }

            // PlayerInfo - Player Name String '...'
            else if (line.startsWith("PlayerInfo - Player Name String")) {
                val name = stringValue(line)
                val playerNamePair = "PlayerInfo - Player Name" to name.node
                val teamIndexPair = "PlayerInfo - TeamIndex" to intNode(nextLineLast())
                elem[name] = TupleNode(playerNamePair, teamIndexPair)
            }

            // PlayerList Int ...
            else if (line.startsWith("PlayerList Int")) {
                parseNextList("PlayerList", ::PlayerListElement)
            }

            // WorldObjects Int ...
            else if (line.startsWith("WorldObjects Int")) {
                parseNextList("WorldObjects", ::WorldObjectsListElement)
            }

            // Points Int ...
            else if (line.startsWith("Points Int")) {
                parseNextList("Points", ::PointsListElement)
            }

            // Num Groups Int ...
            else if (line.startsWith("Num Groups Int")) {
                parseNextList("Num Groups", ::GroupsListElement)
            }

            // Rule List Int ...
            else if (line.startsWith("Rule List Int")) {
                parseNextList("Rule List", ::RuleListElement)
            }

            // 0... ...
            else if (firstWord.toIntOrNull() != null) {
                val name = nestedName(line, firstWord)
                val nested = if (name == "CurrentMix" || name == "UserMix") MixNode() else NestedNode()
                parseNextNested(nested)
                elem[name] = nested
            }

            // ... Matrix33( ..., ..., ..., ..., ..., ..., ..., ..., ... )
            else if (line.contains("Matrix33")) {
                val (name, floats) = floatTupleSplit(line, "Matrix33")
                val values = floats.toList()
                elem[name] = Matrix(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8]).node
            }

            // ... Colour( ..., ..., ..., ... )
            else if (line.contains("Colour")) {
                val (name, floats) = floatTupleSplit(line, "Colour")
                elem[name] = floats.toList().let { Color(it[0], it[1], it[2], it[3]).node }
            }

            // ... Vector3( ..., ..., ... )
            else if (line.contains("Vector3")) {
                val (name, floats) = floatTupleSplit(line, "Vector3")
                val (x, y, z) = floats.toList()
                elem[name] = Vector(x, y, z).node
            }

            // ... Coord( ..., ... )
            else if (line.contains("Coord")) {
                val (name, floats) = floatTupleSplit(line, "Coord")
                elem[name] = floats.toList().let { Coord(it[0], it[1]).node }
            }

            // ... String '...'
            else if (lastWord.contains("'")) {
                elem[scalarName("String")] = stringValue(line).node
            }

            else {
                when (splitWhitespace[splitWhitespace.size - 2]) {
                    // ... Bool ...
                    "Bool" -> elem[scalarName("Bool")] = boolNode(lastWord)

                    // ... Int ...
                    "Int" -> elem[scalarName("Int")] = intNode(lastWord)

                    // ... Float ...
                    "Float" -> elem[scalarName("Float")] = floatNode(lastWord)

                    // ... Double ...
                    "Double" -> elem[scalarName("Double")] = doubleNode(lastWord)
                }
            }
        }
    }
}
