import java.io.File

var accumulator = 0
val visitedIndexes = mutableSetOf<Int>()
fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    val instructions = mutableMapOf<Int, String>()

    for ((index, line) in lines.withIndex()) {
        instructions[index] = line
    }

    var index = 0
    while (!visitedIndexes.contains(index) && index < instructions.size) {
        visitedIndexes.add(index)
        index = eval(instructions[index]!!, index)
    }
    if (visitedIndexes.contains(index)) {
        println("infinite loop")
    }
    println(index)
    println(accumulator)
}

fun eval(command: String, index: Int): Int {
    val data = command.split(" ")
    when {
        data[0] == "nop" -> {
            println("nop index: $index")
            return index + 1
        }
        data[0] == "acc" -> {
            val num = data[1].toCharArray()
            if (num[0] == '+') {
                accumulator += data[1].replace("+", "").toInt()
            } else if (num[0] == '-') {
                accumulator -= data[1].replace("-", "").toInt()
            }
            return index + 1
        }
        data[0] == "jmp" -> {
            println("jmp index: $index")
            val num = data[1].toCharArray()
            if (num[0] == '+') {
                return index + data[1].replace("+", "").toInt()
            } else if (num[0] == '-') {
                return index - data[1].replace("-", "").toInt()
            }
            println("no jmp :(")
            return index
        }
        else -> {
            println("can't handle that command :(")
            return 0
        }
    }
}

