import java.io.File

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
    while (!winner) {
        val p1 = player1Cards.first()
        val p2 = player2Cards.first()

        player1Cards.remove(p1)
        player2Cards.remove(p2)

        if (p1 > p2) {
            player1Cards.add(p1)
            player1Cards.add(p2)
        } else {
            player2Cards.add(p2)
            player2Cards.add(p1)
        }

        rounds++
        if (player1Cards.isEmpty() || player2Cards.isEmpty()) {
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

