import java.io.File
import java.util.*

fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()

    var populatePlayer = 1

    var player1Cards = mutableListOf<Int>()
    var player2Cards = mutableListOf<Int>()

    for ((index, line) in lines.withIndex()) {
        when {
            line.isBlank() -> {
                //skip
            }
            line.contains("Player 1") -> {
                populatePlayer = 1
            }
            line.contains("Player 2") -> {
                populatePlayer = 2
            }
            populatePlayer == 1 -> {
                player1Cards.add(line.toInt())
            }
            populatePlayer == 2 -> {
                player2Cards.add(line.toInt())
            }
        }
    }

    var winner = false
    var rounds = 0
    val hashes = mutableListOf<Int>()
    while (!winner) {

        println("Root game start: $player1Cards $player2Cards")
        val hash = Objects.hash(player1Cards, player2Cards)
        if (!hashes.contains(hash)) {
            hashes.add(hash)

            val p1 = player1Cards.first()
            val p2 = player2Cards.first()
            player1Cards.remove(p1)
            player2Cards.remove(p2)

            if (p1 <= player1Cards.size && p2 <= player2Cards.size) {
                when (combat(1, player1Cards, p1, player2Cards, p2)) {
                    1 -> {
                        player1Cards.add(p1)
                        player1Cards.add(p2)
                    }
                    2 -> {
                        player2Cards.add(p2)
                        player2Cards.add(p1)
                    }
                    else -> {
                        println("oh no")
                    }
                }
            } else {
                if (p1 > p2) {
                    player1Cards.add(p1)
                    player1Cards.add(p2)
                } else {
                    player2Cards.add(p2)
                    player2Cards.add(p1)
                }
            }

            rounds++
            println("Root game result: $rounds, $player1Cards $player2Cards")
            if (player1Cards.isEmpty() || player2Cards.isEmpty()) {
                winner = true
            }
        } else {
            player2Cards.clear()
            winner = true
        }
    }
    var result = 0
    if (player1Cards.isEmpty()) {
        for ((index, c) in player2Cards.reversed().withIndex()) {
            result += c * (index + 1)
        }
    }
    if (player2Cards.isEmpty()) {
        for ((index, c) in player1Cards.reversed().withIndex()) {
            result += c * (index + 1)
        }
    }

    println("Rounds: $rounds, Result: $result")
}

fun combat(gameNo: Int, p1C: MutableList<Int>, p1N: Int, p2C: MutableList<Int>, p2N: Int): Int {
    var player1Cards = p1C.toMutableList()
    var player2Cards = p2C.toMutableList()
    while (player1Cards.size != p1N) {
        player1Cards.removeLast()
    }
    while (player2Cards.size != p2N) {
        player2Cards.removeLast()
    }
    println("Recursive game [$gameNo] start: $player1Cards $player2Cards")
    var winner = false
    var rounds = 0
    val hashes = mutableListOf<Int>()
    while (!winner) {

        val hash = Objects.hash(player1Cards, player2Cards)
        if (!hashes.contains(hash)) {
            hashes.add(hash)

            val p1 = player1Cards.first()
            val p2 = player2Cards.first()
            player1Cards.remove(p1)
            player2Cards.remove(p2)

            if (p1 <= player1Cards.size && p2 <= player2Cards.size) {
                when (combat(gameNo + 1, player1Cards, p1, player2Cards, p2)) {
                    1 -> {
                        player1Cards.add(p1)
                        player1Cards.add(p2)
                    }
                    2 -> {
                        player2Cards.add(p2)
                        player2Cards.add(p1)
                    }
                    else -> {
                        println("oh no")
                    }
                }
            } else {
                if (p1 > p2) {
                    player1Cards.add(p1)
                    player1Cards.add(p2)
                } else {
                    player2Cards.add(p2)
                    player2Cards.add(p1)
                }
            }

            rounds++
            println("Recursive game [$gameNo] result: $rounds, $player1Cards $player2Cards")
            if (player1Cards.isEmpty() || player2Cards.isEmpty()) {
                winner = true
            }
        } else {
            player2Cards.clear()
            winner = true
        }
    }
    if (player1Cards.isEmpty()) {
       return 2
    }
    if (player2Cards.isEmpty()) {
        return 1
    }
    return -1
}

