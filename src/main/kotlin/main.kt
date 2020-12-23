import java.io.File
import java.util.*

fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    val spoken = mutableListOf<Long>()
    for ((index, line) in lines.withIndex()) {
        val nums = line.split(",")
        for (n in nums) {
            spoken.add(n.toLong())
        }
    }

    var curr = 0L
    while (spoken.size < 2020) {
        spoken.add(curr)
        var secondLastSpoken = -1L
        var lastSpoken = -1L
        for ((index, s) in spoken.withIndex()) {
            if (s == curr) {
                secondLastSpoken = lastSpoken
                lastSpoken = index + 1L
            }
        }
        curr = if (secondLastSpoken == -1L) {
            0L
        }  else {
            lastSpoken - secondLastSpoken
        }
    }
    println(spoken.last())
}

