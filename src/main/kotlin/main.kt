import java.io.File
import java.lang.Math.abs


fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")
    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()

    var cardpk = 0L
    var cardls = 0
    val cardsn = 7
    var doorpk = 0L
    var doorls = 0
    val doorsn = 7
    for ((index, line) in lines.withIndex()) {
        if (cardpk == 0L) {
            cardpk = line.toLong()
        } else {
            doorpk = line.toLong()
        }
    }

    var start = 1L
    while (start != cardpk) {
        start *= cardsn
        start %= 20201227
        cardls++
    }
    println("Card ls: $cardls")

    start = 1L
    while (start != doorpk) {
        start *= doorsn
        start %= 20201227
        doorls++
    }

    println("Door ls: $doorls")

    var cardprvk = 1L
    var loop = 0
    while (loop < doorls) {
        cardprvk *= cardpk
        cardprvk %= 20201227
        loop++
    }
    println("Card prvk: $cardprvk")

    var doorprvk = 1L
    loop = 0
    while (loop < cardls) {
        doorprvk *= doorpk
        doorprvk %= 20201227L
        loop++
    }
    println("Door prvk: $doorprvk")


}

