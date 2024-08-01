package dodd.terran.value

enum class Formation {
    NONE,
    SINGLE,
    SIDE_BY_SIDE,
    DIAGONAL_RIGHT,
    DIAGONAL_LEFT,
    LINE,
    DIAGONAL_V,
    DIAGONAL_V_ALT,
    OVERLAP;

    override fun toString() = "Formation.$name"
}
