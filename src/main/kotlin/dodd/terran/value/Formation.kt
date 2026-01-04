package dodd.terran.value

enum class Formation(val string: String?) {
    NONE("None"),
    SINGLE(null),
    LINE_ABREAST("LineAbreast"),
    ECHELON_RIGHT("EchelonRight"),
    ECHELON_LEFT("EchelonLeft"),
    COLUMN("Column"),
    DIAMOND("Diamond"),
    CONVOY("Convoy"),
    OVERLAP(null);

    override fun toString() = "Formation.$name"
}
