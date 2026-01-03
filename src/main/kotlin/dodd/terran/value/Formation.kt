package dodd.terran.value

enum class Formation {
    NONE,
    SINGLE,
    LINE_ABREAST,
    ECHELON_RIGHT,
    ECHELON_LEFT,
    COLUMN,
    DIAMOND,
    CONVOY,
    OVERLAP;

    override fun toString() = "Formation.$name"
}
