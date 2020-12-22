import java.io.File
import java.lang.Exception


var plan = mutableListOf<MutableList<Char>>()
fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()

    for ((index, line) in lines.withIndex()) {
        val listLine = mutableListOf<Char>()
        for (c in line) {
            listLine.add(c)
        }
        plan.add(listLine)
    }
    var iterations = 0
    while (updateSeats()) {
        iterations++
    }
    println(plan)
    println(iterations)
    println(countOccupied())

}

fun updateSeats(): Boolean {

    val newPlan = mutableListOf<MutableList<Char>>()
    for (c in plan) {
        newPlan.add(c.toMutableList())
    }
    for ((indexR, a) in plan.withIndex()) {
        for ((indexC, b) in a.withIndex()) {
            var surroundingOccupied = 0
            var seen = false
            var counter = 0
            while (!seen) {
                counter++
                seen = isChair(indexR, indexC + counter)
            }
            if (isOccupied(indexR, indexC + counter)) {
                surroundingOccupied++
            }

            seen = false
            counter = 0
            while (!seen) {
                counter++
                seen = isChair(indexR, indexC - counter)
            }
            if (isOccupied(indexR, indexC - counter)) {
                surroundingOccupied++
            }

            seen = false
            counter = 0
            while (!seen) {
                counter++
                seen = isChair(indexR + counter, indexC)
            }
            if (isOccupied(indexR + counter, indexC)) {
                surroundingOccupied++
            }

            seen = false
            counter = 0
            while (!seen) {
                counter++
                seen = isChair(indexR - counter, indexC)
            }
            if (isOccupied(indexR - counter, indexC)) {
                surroundingOccupied++
            }

            seen = false
            counter = 0
            while (!seen) {
                counter++
                seen = isChair(indexR - counter, indexC - counter)
            }
            if (isOccupied(indexR - counter, indexC - counter)) {
                surroundingOccupied++
            }

            seen = false
            counter = 0
            while (!seen) {
                counter++
                seen = isChair(indexR + counter, indexC + counter)
            }
            if (isOccupied(indexR + counter, indexC + counter)) {
                surroundingOccupied++
            }

            seen = false
            counter = 0
            while (!seen) {
                counter++
                seen = isChair(indexR - counter, indexC + counter)
            }
            if (isOccupied(indexR - counter, indexC + counter)) {
                surroundingOccupied++
            }

            seen = false
            counter = 0
            while (!seen) {
                counter++
                seen = isChair(indexR + counter, indexC - counter)
            }
            if (isOccupied(indexR + counter, indexC - counter)) {
                surroundingOccupied++
            }

            if (plan[indexR][indexC] == 'L') {
                if (surroundingOccupied == 0) {
                    newPlan[indexR][indexC] = '#'
                }
            } else if (plan[indexR][indexC] == '#') {
                if (surroundingOccupied >= 5) {
                    newPlan[indexR][indexC] = 'L'
                }
            }
        }
    }
    if (!plan.containsAll(newPlan)) {
        plan = newPlan
        return true
    }
    return false
}

fun isChair(r: Int, c: Int): Boolean {
    return try {
        plan[r][c] == 'L' || plan[r][c] == '#'
    } catch (e: Exception) {
        true
    }
}

fun isOccupied(r: Int, c: Int): Boolean {
    return try {
        plan[r][c] == '#'
    } catch (e: Exception) {
        false
    }
}

fun countOccupied(): Int {
    var counter = 0
    for ((indexR, a) in plan.withIndex()) {
        for ((indexC, b) in a.withIndex()) {
            if (b == '#') {
                counter++
            }
        }
    }
    return counter
}

class Floor : Exception() {

}

