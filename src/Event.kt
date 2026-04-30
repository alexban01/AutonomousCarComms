enum class EventType {
    ACCIDENT,
    EMERGENCY_BRAKE,
    POTHOLE,
    HIGH_TRAFFIC,
    DISTRACTED_DRIVER
}

data class Event(
    val vehicleVin: String,
    val type: EventType,
    val details: String,
    val location: Location,
    val odometer_value: Int
) {
    fun print() {
        println(vehicleVin + ": " +
                type.toString() +
                " -- ${details}" +
                " -- ${location}" +
                " -- Odometer Value: ${odometer_value}")
    }
}