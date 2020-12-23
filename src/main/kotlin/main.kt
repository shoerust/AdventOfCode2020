import java.io.File
import java.util.*

class Number {
    var lastSpoken = 0L
    var secondLastSpoken = 0L
}

fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    val spoken = mutableListOf<Long>()
    val numCache = mutableMapOf<Long, Number>()
    for ((index, line) in lines.withIndex()) {
        val nums = line.split(",")
        for ((numIndex, n) in nums.withIndex()) {
            spoken.add(n.toLong())
            val number = Number()
            number.lastSpoken = numIndex + 1L
            number.secondLastSpoken = -1L
            numCache[n.toLong()] = number
        }
    }

    var curr = 0L

    while (spoken.size < 30_000_000) {
        spoken.add(curr)
        var secondLastSpoken = -1L
        var lastSpoken = -1L
        if (numCache.containsKey(curr)) {
            val number = numCache[curr]!!
            secondLastSpoken = number.lastSpoken
            lastSpoken = spoken.size.toLong()
            number.secondLastSpoken = secondLastSpoken
            number.lastSpoken = lastSpoken
            curr = lastSpoken - secondLastSpoken
        } else {
            val number = Number()
            number.lastSpoken = spoken.size.toLong()
            number.secondLastSpoken = -1L
            numCache[curr] = number
            curr = 0L
        }
    }
    println(spoken.last())
}

