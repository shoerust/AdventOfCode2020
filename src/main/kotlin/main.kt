import java.io.File

fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()

    val data = mutableListOf<Long>()
    val preambleSize = 25
    val preamble = mutableListOf<Long>()
    for ((index, line) in lines.withIndex()) {
        data.add(line.toLong())
    }
    for (value in data) {
        if (preamble.size == preambleSize) {
           if (!valid(value, preamble)) {
               println("Invalid: $value, $preamble")
               break
           }
        }
        preamble.add(value)
        if (preamble.size > preambleSize) {
            preamble.removeFirst()
        }
    }
}

fun valid(value: Long, preamble: MutableList<Long>): Boolean {
    for (p in preamble) {
        if (preamble.contains(value - p)) {
            return true
        }
    }
    return false
}

