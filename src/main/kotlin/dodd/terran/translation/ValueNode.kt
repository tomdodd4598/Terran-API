package dodd.terran.translation

import dodd.terran.value.Matrix
import dodd.terran.util.Helpers.definitionString
import dodd.terran.value.Color
import dodd.terran.value.Coord
import dodd.terran.value.Vector

class StringNode(var value: String) : Node() {

    override fun write(db: DefinitionBuilder, name: String) {
        db.append("$name String '$value'")
    }

    override fun toString() = "\"$value\""
}

class BoolNode(var value: Boolean) : Node() {

    override fun write(db: DefinitionBuilder, name: String) {
        db.append("$name Bool ${if (value) "True" else "False"}")
    }

    override fun toString() = value.toString()
}

class IntNode(var value: Int) : Node() {

    override fun write(db: DefinitionBuilder, name: String) {
        db.append("$name Int $value")
    }

    override fun toString() = value.toString()
}

class FloatNode(var value: Float) : Node() {

    override fun write(db: DefinitionBuilder, name: String) {
        db.append("$name Float ${value.definitionString()}")
    }

    override fun toString() = "${value}f"
}

class DoubleNode(var value: Double) : Node() {

    override fun write(db: DefinitionBuilder, name: String) {
        db.append("$name Double ${value.definitionString()}")
    }

    override fun toString() = "${value}d"
}

class CoordNode(var value: Coord) : Node() {

    override fun write(db: DefinitionBuilder, name: String) {
        db.append("$name ${value.asSequence().definitionString("Coord")}")
    }

    override fun toString() = value.toString()
}

class VectorNode(var value: Vector) : Node() {

    override fun write(db: DefinitionBuilder, name: String) {
        db.append("$name ${value.asSequence().definitionString("Vector3")}")
    }

    override fun toString() = "Vector$value"
}

class ColorNode(var value: Color) : Node() {

    override fun write(db: DefinitionBuilder, name: String) {
        db.append("$name ${value.asSequence().definitionString("Colour")}")
    }

    override fun toString() = value.toString()
}

class MatrixNode(var value: Matrix) : Node() {

    override fun write(db: DefinitionBuilder, name: String) {
        db.append("$name ${value.asSequence().flatMap { it.asSequence() }.definitionString("Matrix33")}")
    }

    override fun toString() = "Matrix$value"
}
