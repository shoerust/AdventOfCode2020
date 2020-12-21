import java.io.File

var min = 0L
var max = 0L
fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()

    val data = mutableListOf<Long>()
    val invalid = 144381670L
    for ((index, line) in lines.withIndex()) {
        data.add(line.toLong())
    }

    while (!eval(invalid, data)) {
        data.removeFirst()
    }
    val sum = min + max
    println("Min: $min, Max: $max")
    println("Sum: $sum")
}

fun eval(invalid: Long, data: MutableList<Long>): Boolean {
    var sum = 0L
    for (v in data) {
        if (min == 0L) {
            min = v
        }
        if (v < min) {
            min = v
        }
        if (v > max) {
            max = v
        }
        sum += v
        if (sum == invalid) {
            return true
        }
        if (sum > invalid) {
            min = 0L
            max = 0L
            break
        }
    }
    return false
}

