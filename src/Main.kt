fun main() {
    // create cars
    val car1 = Car(
        "Dacia",
        "iLogan",
        "31231312",
        0,
        15000,
        Location(45.0, 37.0)
    )

    val car2 = Car(
        "Dacia",
        "iSandero",
        "47328261",
        0,
        25000,
        Location(32.0, 87.0)
    )

    val car3 = Car(
        "Skoda",
        "iOctavia",
        "876252521",
        0,
        17000,
        Location(15.0, 10.0)
    )

    val car4 = Car(
        "BMW",
        "i8",
        "1438327234",
        0,
        21000,
        Location(25.0, 50.0)
    )

    // add events
    // car1
    car1.createEvent(
        EventType.EMERGENCY_BRAKE,
        "Car's speed to high",
        car1.getLocation()
    )
    car1.increaseOdometer(50)

    //car2
    car2.createEvent(
        EventType.EMERGENCY_BRAKE,
        "Car's speed to high",
        car2.getLocation()
    )
    car2.setLocation(31.0, 86.6)
    car2.increaseOdometer(50)
    car2.createEvent(
        EventType.DISTRACTED_DRIVER,
        "Driver didn't hold the wheel for 1 km",
        car2.getLocation()
    )

    //car3
    car3.createEvent(
        EventType.ACCIDENT,
        "An accident has been detected. Emergency services have been called",
        car3.getLocation()
    )

    //car4
    car4.setLocation(26.0, 52.0)
    car4.setSpeed(60)
    car4.createEvent(
        EventType.HIGH_TRAFFIC,
        "This area has high traffic. Current speed:" + car4.getSpeed(),
        car4.getLocation()
    )
    car4.increaseOdometer(80)
    car4.setSpeed(100)
    car4.createEvent(
        EventType.POTHOLE,
        "Large pothole detected. I should tell other cars about it",
        car4.getLocation()
    )
    car4.setLocation(27.0, 54.0)
    car4.increaseOdometer(30)
    car4.setSpeed(40)
    car4.createEvent(
        EventType.HIGH_TRAFFIC,
        "This area has high traffic. Current speed:" + car4.getSpeed(),
        car4.getLocation()
    )

    for (event in car4.getLastEvents(100)) {
        event.print()
    }

    // info
    println(" ==== Car 4 ==== ")
    car1.sendInfo(car4)
    car2.sendInfo(car4)
    car3.sendInfo(car4)
    println("Closest car: ")
    car4.pickClosestCar()?.printInfo()
    println("Events received from other cars: ")
    for (event in car4.getReceivedEvents()) {
        event.print()
    }
    println(" ==== End of Car 4 ==== ")
    println()
    println()


    println(" ==== Car 1 ==== ")
    car2.sendInfo(car1)
    car3.sendInfo(car1)
    car4.sendInfo(car1)
    println("Closest car: ")
    car1.pickClosestCar()?.printInfo()
    println(" ==== End of Car 1 ==== ")
    println()
    println()

    println(" ==== Car 2 ==== ")
    car3.sendInfo(car2)
    car1.sendInfo(car2)
    car4.sendInfo(car2)
    println("Closest car: ")
    car2.pickClosestCar()?.printInfo()
    println(" ==== End of Car 2 ==== ")
    println()
    println()
}