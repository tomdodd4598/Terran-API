package dodd.terran.value

enum class Equivalence(val string: String) {
    EQUAL_TO("Equal To"),
    LESS_THAN("Less Than"),
    GREATER_THAN("Greater Than");

    override fun toString() = "Equivalence.$name"
}
