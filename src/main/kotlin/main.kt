import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    var idList = mutableListOf<Int>()

    var lineCount = 0
    var yesQuestions = mutableMapOf<String, Int>()
    var sum = 0
    for (line in lines) {
        if (line.isNotBlank()) {
            lineCount++
            for (q in line) {
                if (yesQuestions.containsKey(q.toString())) {
                    yesQuestions[q.toString()] = yesQuestions[q.toString()]!!.plus(1)
                } else {
                    yesQuestions[q.toString()] = 1
                }
            }
        } else {
            val temp = mutableMapOf<String, Int>()
            temp.putAll(yesQuestions)
            for (pair in yesQuestions) {
                if (pair.value == lineCount) {
                    temp.remove(pair.key)
                }
            }
            sum += yesQuestions.size - temp.size
            lineCount = 0
            yesQuestions = mutableMapOf<String, Int>()
        }
    }
    val temp = mutableMapOf<String, Int>()
    temp.putAll(yesQuestions)
    for (pair in yesQuestions) {
        if (pair.value == lineCount) {
            temp.remove(pair.key)
        }
    }
    sum += yesQuestions.size - temp.size
    lineCount = 0
    println(sum)
}
