import java.io.File

fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    var timestamp = 0L
    for ((index, line) in lines.withIndex()) {

        if (index == 0) {
          timestamp = line.toLong()
        } else {
            val buses = line.split(",")
            var shortestWait = timestamp
            var soonestNo = -1
            var ride = 0L
            for (bus in buses) {
                if (bus != "x") {
                    val longBus = bus.toLong()
                    val minutes = longBus - (timestamp % longBus)
                    if (minutes < shortestWait) {
                        shortestWait = minutes
                        ride = longBus
                    }
                }
            }
            println(shortestWait * ride)
        }
    }
}

