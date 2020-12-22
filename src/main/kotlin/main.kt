import java.io.File
import java.lang.Math.abs

val regex = """([N|S|E|W|L|R|F])([0-9]+)""".toRegex()
fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()

    for ((index, line) in lines.withIndex()) {
        val matchResult = regex.find(line)
        val direction = matchResult!!.groupValues[1]
        val value = matchResult!!.groupValues[2].toLong()

        when (direction) {
            "N" -> {
                Waypoint.y += value
            }
            "S" -> {
                Waypoint.y -= value
            }
            "E" -> {
                Waypoint.x += value
            }
            "W" -> {
                Waypoint.x -= value
            }
            "L" -> {
                for (i in 1..value/90) {
                    val temp = Waypoint.y
                    Waypoint.y = Waypoint.x
                    Waypoint.x = temp * -1
                }
            }
            "R" -> {
                for (i in 1..value/90) {
                    val temp = Waypoint.y
                    Waypoint.y = Waypoint.x * -1
                    Waypoint.x = temp
                }
            }
            "F" -> {
                Ship.x += Waypoint.x * value
                Ship.y += Waypoint.y * value
            }
            else -> {
                println("wtf")
            }
        }
    }
    val result = abs(Ship.x) + abs(Ship.y)
    println("x: ${Ship.x}, y: ${Ship.y}, result: $result")
}

object Ship {
    var x = 0L
    var y = 0L
}

object Waypoint {
    var x = Ship.x + 10L
    var y = Ship.y + 1L
}

