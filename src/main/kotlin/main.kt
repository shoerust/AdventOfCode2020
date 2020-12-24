import java.io.File

fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    val rightCupByCup = mutableMapOf<Int, Int>()
    val max = 1000000
    var leftCup = -1
    var firstCup = -1
    for ((index, line) in lines.withIndex()) {
        for (c in line.toCharArray()) {
            if (leftCup > -1) {
                rightCupByCup[leftCup] = c.toString().toInt()
            } else {
                firstCup = c.toString().toInt()
            }
            leftCup = c.toString().toInt()
        }
    }

    for (i in 10..1000000) {
        rightCupByCup[leftCup] = i
        leftCup = i
    }
    rightCupByCup[leftCup] = firstCup

    var move = 0
    var currentCup = 5
    while (move < 10000000) {
        //println(rightCupByCup.keys)
        //println(currentCup)

            if (move % 100000 == 0) {
               //println("Move $move")
            }

        val p1 = rightCupByCup[currentCup]!!
        val p2 = rightCupByCup[p1]!!
        val p3 = rightCupByCup[p2]!!

        //println("$p1 $p2 $p3")

        var dest = -1
        var diff = 1
        var candidate = currentCup - 1
        while (dest == -1) {
            if (candidate == 0) {
                candidate = max
            }
            if (candidate != p1 && candidate != p2 && candidate != p3) {
                dest = candidate
            }
            candidate--
        }
        //println("Dest $dest")
        val n = rightCupByCup[dest]!!
        val l = rightCupByCup[p3]!!
        rightCupByCup[dest] = p1
        rightCupByCup[p1] = p2
        rightCupByCup[p2] = p3
        rightCupByCup[p3] = n
        rightCupByCup[currentCup] = l
        currentCup = l
        move++
    }
    var i = 1
    var done = false
//    while (!done) {
//        i = rightCupByCup[i]!!
//        print(" ")
//        if (i == 1) {
//            done = true
//        }
//    }
    val t = rightCupByCup[1]!!
    val t2 = rightCupByCup[t]!!
    val result = t.toLong() * t2.toLong()
    println("$t $t2 $result")
    //24987653
}

