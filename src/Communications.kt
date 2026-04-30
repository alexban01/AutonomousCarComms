interface Communications {
    fun sendInfo(other: Communications)
    fun receiveInfo(other_car: Communications)
    fun printInfo()
    fun getVin(): String

    fun getLastEvents(lastKm: Int = 100): List<Event>
    fun getLocation(): Location
}