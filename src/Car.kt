import kotlin.math.pow

interface Vehicle {
    fun sendInfo(other: Vehicle)
    fun receiveInfo(other_car: Vehicle)
    fun printInfo()
    fun getVin(): String

    fun getLastEvents(lastKm: Int = 100): List<Event>
    fun getLocation(): Location
}

class Car(
    private val manufacturer: String,
    private val model: String,
    private val vin: String,
    private var current_speed: Int,
    private var odometer: Int,
    private var location: Location,
): Vehicle {
    private var events: MutableList<Event> = mutableListOf()
    private var receivedEvents: MutableList<Event> = mutableListOf()
    private var knownCars: MutableList<Vehicle> = mutableListOf()

    fun setSpeed(speed: Int) {
        if (speed >= 0)
            current_speed = speed;
    }

    fun getSpeed(): Int {
        return current_speed
    }

    override fun getVin(): String {
        return vin
    }

    fun increaseOdometer(value: Int) {
        if (value >= 0)
            odometer += value
    }

    fun setLocation(lat: Double, long: Double) {
        location = Location(lat, long)
    }

    override fun getLocation(): Location {
        return location
    }

    fun distanceTo(other_car: Vehicle): Double {
        val distance = (
                (location.lat - other_car.getLocation().lat).pow(2) +
                        (location.long - other_car.getLocation().long).pow(2)
                ).pow(0.5)
        return distance
    }

    override fun sendInfo(other_car: Vehicle) {
        other_car.receiveInfo(this)
    }

    override fun receiveInfo(other_car: Vehicle) {
        knownCars.add(other_car)
        receivedEvents.addAll(other_car.getLastEvents())
    }

    override fun printInfo() {
        println("-------------------------")
        println("Manufacturer: $manufacturer")
        println("Model: $model")
        println("VIN: $vin")
        println("Current Speed: $current_speed")
        println("Odometer: $odometer")
        println("Location: ${location.lat}, ${location.long}")
        println("-------------------------")
    }

    fun pickClosestCar(): Vehicle? {
        var closestCar: Vehicle? = null
        var closestDistance: Double = 100000000.0
        for (car in knownCars) {
            val distance = distanceTo(car)
            if (closestDistance > distance) {
                closestDistance = distance
                closestCar = car
            }
        }
        return closestCar
    }

    fun createEvent(type: EventType, details: String, location: Location) {
        val event = Event(vin, type, details, location, odometer)
        events.addLast(event)
    }

    override fun getLastEvents(lastKm: Int): List<Event> {
        val listOfEvents = mutableListOf<Event>()
        for (event in events) {
            if (event.odometer_value > (odometer - lastKm))
                listOfEvents.add(event)
        }
        return listOfEvents
    }

    fun getReceivedEvents(): List<Event> {
        return receivedEvents
    }
}