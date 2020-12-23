import java.io.File

fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    val cups = mutableListOf<Int>()
    for ((index, line) in lines.withIndex()) {
         for (c in line.toCharArray()) {
             cups.add(c.toString().toInt())
         }
    }
    var min = -1
    var max = -1
    var minIndex = 10000
    var maxIndex = -1
    for ((index, c) in cups.withIndex()) {
        if (c > max) {
            max = c
            maxIndex = index
        }
        if (c < min) {
            min = c
            minIndex = index
        }
    }

    println(cups)
    var move = 0
    var currIndex = 0
    while (move < 100) {
        //println("Curr index: $currIndex")
        val currentCup = cups[currIndex]
        println("Curr cup: $currentCup")

       val selection = mutableListOf<Int>()
        for (c in 1..3) {
            var d = currIndex + c
            if (d >= cups.size) {
                d -= cups.size
            }
            selection.add(cups[d])
        }
        
        for (c in 0..2) {
            cups.remove(selection[c])
        }

        println("Selection: $selection")
        println("Remaining: $cups")
        min = 100000
        max = -1
        minIndex = 10000
        maxIndex = -1
        for ((index, c) in cups.withIndex()) {
            if (c > max) {
                max = c
                maxIndex = index
            }
            if (c < min) {
                min = c
                minIndex = index
            }
        }
        var dest = -1
        var destIndex = -1
        var diff = 1
        while (dest == -1) {
            for ((index, c) in cups.withIndex()) {
                if (c == currentCup - diff) {
                    dest = c
                    destIndex = index
                    break
                }
            }
            if (dest == -1) {
                diff++
                if (currentCup - diff < min) {
                    for ((index, c) in cups.withIndex()) {
                        if (c > dest) {
                            dest = c
                            destIndex = index
                        }
                    }
                }
            }
        }
        println("Dest: $dest")

        var part1 = mutableListOf<Int>()
        for ((index, c) in selection.withIndex()) {
            cups.add(destIndex+index+1, c)
        }
        println(cups)
        move++
        for ((index, c) in cups.withIndex()) {
           if (c == currentCup) {
               currIndex = index + 1
           }
        }

        if (currIndex == cups.size) {
            currIndex = 0
        }
    }
    //24987653
}

