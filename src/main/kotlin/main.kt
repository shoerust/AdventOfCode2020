import java.io.File

var oneJolt = 0L
var threeJolt = 0L
fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    val data = mutableListOf<Long>()
    for ((index, line) in lines.withIndex()) {
        data.add(line.toLong())
    }
    data.sort()

    val adapters = mutableListOf<Long>()

    for (d in data) {
        if (adapters.isEmpty()) {
            if (d <= 3L) {
                if (d == 1L) {
                    oneJolt++
                }
                if (d == 3L) {
                    threeJolt++
                }
                adapters.add(d)
            }
        } else {
            if (d - adapters.last() <= 3) {
                if (d - adapters.last() == 1L) {
                    oneJolt++
                }
                if (d - adapters.last() == 3L) {
                    threeJolt++
                }
                adapters.add(d)
            } else {
                break
            }
        }
    }
    threeJolt++
    val result = oneJolt * threeJolt
    println("Result: $result, One: $oneJolt, Three: $threeJolt")

}

