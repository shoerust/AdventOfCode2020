import java.io.File

var oneJolt = 0L
var threeJolt = 0L
val data = mutableListOf<Long>()
fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()

    data.add(0)
    for ((index, line) in lines.withIndex()) {
        data.add(line.toLong())
    }
    data.sort()
    data.add(data.last() + 3)

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

    println("Part 2 -----")

   println(calculate(0))
}

var memo = mutableMapOf<Int, Long>()
fun calculate(index: Int): Long {
    if (index == data.size - 1) {
        return 1
    }
    if (memo.containsKey(index)) {
        return memo[index]!!
    }
    var count = 0L
    for (j in index+1 until data.size) {
        if ((data[j] - data[index]) <= 3) {
            count += calculate(j)
        }
    }
    memo[index] = count
    return count
}

