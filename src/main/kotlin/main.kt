import java.io.File

fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    val tilePaths  = mutableListOf<List<String>>()
    val directions = mutableListOf<String>("e", "se", "sw", "w", "nw", "ne")
    val tiles = mutableMapOf<Coord, Tile>()
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
        for (d in path) {
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
        if (tiles.containsKey(c)) {
            val tile = tiles[c]!!
            if (tile.colour == "white") {
                tile.colour = "black"
            } else {
                tile.colour = "white"
            }
            tiles[c] = tile
        } else {
            val t = Tile()
            t.colour = "black"
            tiles[c] = t
        }
    }

    var counter = 0
    for ((c, v) in tiles) {
        if (v.colour == "black") {
            counter++
        }
    }
    println(tiles)
    println("black $counter")
}

class Coord {
    var x = 0
    var y = 0
    var z = 0
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

    override fun toString(): String {
        return "Coord(x=$x, y=$y, z=$z)"
    }


}

class Tile {
    var colour = "white"
    override fun toString(): String {
        return "Tile(colour='$colour')"
    }
}

