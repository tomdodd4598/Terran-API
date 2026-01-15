package dodd.terran.value

enum class Skill(val string: String) {
    DEFAULT("CREW SKILL LEVEL"),
    GREEN("Green"),
    AVERAGE("Average"),
    ELITE("Elite"),
    PLAYER("Players"),
    JIM_TORPEDO_BOAT("M01JimTBoat");

    override fun toString() = "Skill.$name"
}
