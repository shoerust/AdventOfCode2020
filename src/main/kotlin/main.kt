import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    var maxSeatId = 0
    var idList = mutableListOf<Int>()
    for (line in lines) {
       val seatId = calculateSeatId(line)
        idList.add(seatId)
       if (seatId > maxSeatId) {
           maxSeatId = seatId
       }
    }
    println(maxSeatId)
    idList.sort()
    var previousId = -1
    for (id in idList) {
        previousId = if (previousId == -1) {
            id
        } else {
            if (id == (previousId + 2)) {
                println("Id: $id")
            }
            id
        }
    }
}


fun calculateSeatId(line: String): Int {
    var rowMax = 127
    var rowMin = 0
    var seatMax = 7
    var seatMin = 0
    var useRowMin = false
    var useSeatMin = false
    var finalRow = 0
    var finalSeat = 0
    for (direction in line) {
        when (direction) {
            'F' -> {
                rowMax -= ((rowMax - rowMin) / 2) + 1
                useRowMin = true
            }
            'B' -> {
                rowMin += ((rowMax - rowMin) / 2) + 1
                useRowMin = false
            }
            'R' -> {
                seatMin += ((seatMax - seatMin) / 2) + 1
                useSeatMin = false
            }
            'L' -> {
                seatMax -= ((seatMax - seatMin) / 2) + 1
                useSeatMin = true
            }
        }
    }
    println(line)
    println("$rowMax $rowMin $seatMax $seatMin")
    finalRow = if (useRowMin) {
        rowMin
    } else {
        rowMax
    }
    finalSeat = if (useSeatMin) {
        seatMin
    } else {
        seatMax
    }
    println("$finalRow $finalSeat")
    val id = finalRow * 8 + finalSeat
    println(id)
    return id
}
