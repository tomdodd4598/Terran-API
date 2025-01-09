package dodd.terran.value

enum class Stance(val string: String) {
    DEFAULT("AI STANCE"),
    DUMMY("Stance_Dummy"),
    PERSISTENT("Stance Persistant"),
    DEFENSIVE("Stance_Defensive"),
    NEUTRAL("Stance_Neutral"),
    AGGRESSIVE("Stance_Aggressive");

    override fun toString() = "Stance.$name"
}
