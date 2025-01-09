package dodd.terran.value

enum class Skill(val string: String) {
    DEFAULT("CREW SKILL LEVEL"),
    GREEN("Green"),
    AVERAGE("Average"),
    ELITE("Elite");

    override fun toString() = "Skill.$name"
}
