package dodd.terran.value

enum class VitalSection(val string: String) {
    SHIP("vitalToShip"),
    MAX_VELOCITY("vitalToMaxVelocity"),
    MANEUVERABILITY("vitalToManeuverability"),
    MISSION("vitalToMission");

    override fun toString() = "VitalSection.$name"
}
