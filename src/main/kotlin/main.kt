import java.io.File


var black = HashSet<Coord>()
fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")
    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    val tilePaths = mutableListOf<List<String>>()
    val directions = mutableListOf<String>("e", "se", "sw", "w", "nw", "ne")

    for ((index, line) in lines.withIndex()) {
        var path = line
        val pathList = mutableListOf<String>()
        while (path.isNotEmpty()) {
            for (d in directions) {
                if (path.startsWith(d)) {
                    pathList.add(d)
                    path = path.removePrefix(d)
                }
            }
        }
        tilePaths.add(pathList)
    }
    println(tilePaths)

    for (path in tilePaths) {
        val c = Coord()
        for ((index, d) in path.withIndex()) {
            when (d) {
                "e" -> {
                    c.x++
                    c.y--
                }
                "se" -> {
                    c.z++
                    c.y--
                }
                "sw" -> {
                    c.x--
                    c.z++
                }
                "w" -> {
                    c.x--
                    c.y++
                }
                "nw" -> {
                    c.z--
                    c.y++
                }
                "ne" -> {
                    c.x++
                    c.z--
                }
            }
        }
        if (black.contains(c)) {
            black.remove(c)
        } else {
            black.add(c)
        }
    }

    println(black.size)

    println("part 2-----")

    var day = 1
    while (day <= 100) {
        val eval = HashSet<Coord>()
        eval.addAll(black)
        val newBlack = HashSet<Coord>()
        for (coord in black) {
            val r = adjacent(coord)
            eval.addAll(r)
        }
        for (c in eval) {
            val r = check(c)
            if (black.contains(c) && (r == 1 || r == 2)) {
                newBlack.add(c)
            }
            if (!black.contains(c) && r == 2) {
                newBlack.add(c)
            }
        }
        println("Day $day black: ${newBlack.size}")
        black = newBlack

        day++
    }
}

fun adjacent(c: Coord): Set<Coord> {

    var check = Coord()
    val tilesToAdd = HashSet<Coord>()
    check.x = c.x
    check.y = c.y
    check.z = c.z

    //s
    check.x++
    check.y--

    tilesToAdd.add(check)

    check = Coord()

    check.x = c.x
    check.y = c.y
    check.z = c.z

    //nw
    check.z++
    check.y--

    tilesToAdd.add(check)

    check = Coord()

    check.x = c.x
    check.y = c.y
    check.z = c.z

    //sw
    check.x--
    check.z++

    tilesToAdd.add(check)

    check = Coord()

    check.x = c.x
    check.y = c.y
    check.z = c.z

    //w
    check.x--
    check.y++

    tilesToAdd.add(check)

    check = Coord()

    check.x = c.x
    check.y = c.y
    check.z = c.z

    //nw
    check.z--
    check.y++

    tilesToAdd.add(check)

    check = Coord()
    check.x = c.x
    check.y = c.y
    check.z = c.z

    //ne"
    check.x++
    check.z--

    tilesToAdd.add(check)

    return tilesToAdd

}

fun check(c: Coord): Int {
    var cc = 0
    var check = Coord()
    check.x = c.x
    check.y = c.y
    check.z = c.z

    //s
    check.x++
    check.y--

    if (black.contains(check)) {
        cc++
    }

    check = Coord()

    check.x = c.x
    check.y = c.y
    check.z = c.z

    //nw
    check.z++
    check.y--

    if (black.contains(check)) {
        cc++
    }

    check = Coord()

    check.x = c.x
    check.y = c.y
    check.z = c.z

    //sw
    check.x--
    check.z++

    if (black.contains(check)) {
        cc++
    }

    check = Coord()

    check.x = c.x
    check.y = c.y
    check.z = c.z

    //w
    check.x--
    check.y++

    if (black.contains(check)) {
        cc++
    }

    check = Coord()

    check.x = c.x
    check.y = c.y
    check.z = c.z

    //nw
    check.z--
    check.y++

    if (black.contains(check)) {
        cc++
    }

    check = Coord()

    check.x = c.x
    check.y = c.y
    check.z = c.z

    //ne"
    check.x++
    check.z--

    if (black.contains(check)) {
        cc++
    }

    return cc

}

class Coord {
    var x = 0
    var y = 0
    var z = 0
    override fun toString(): String {
        return "Coord(x=$x, y=$y, z=$z)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coord

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + z
        return result
    }
}

