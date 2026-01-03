package dodd.terran.value

enum class Banner(val string: String) {
    NONE("No Banner"),
    RED("Banner RedTeam"),
    BLUE("Banner BlueTeam"),
    BLACK("Banner BlackTeam");

    override fun toString() = "Banner.$name"
}
