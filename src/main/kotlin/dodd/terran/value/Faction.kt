package dodd.terran.value

enum class Faction {
    NAVY,
    CIVILIAN,
    PIRATE,
    PROCYON,
    ANY;

    override fun toString() = "Faction.$name"
}
