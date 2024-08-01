package dodd.terran.value

enum class FollowMode(val string: String) {
    TO_END("to end"),
    LOOP("loop"),
    TELEPORT_LOOP("teleport loop");

    override fun toString() = "FollowMode.$name"
}
