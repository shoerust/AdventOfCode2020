import java.io.File

fun main(args: Array<String>) {
    println("Hello World!")
    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    val trees: Long
    val trees2 = evalSlope(lines, 3, 1)
    val trees3 = evalSlope(lines, 1, 1)
    val trees4 = evalSlope(lines, 5, 1)
    val trees5 = evalSlope(lines, 7, 1)
    val trees6 = evalSlope(lines, 1, 2)
    trees = trees2 * trees3 * trees4 * trees5 * trees6
    println(trees)
}

fun evalSlope(lines: List<String>, right: Int, down: Int): Long {
    var position = 0
    var trees = 0L
    var downCounter = 0
    lines.forEach { line -> run {
        if (downCounter == 0 || downCounter % down == 0) {
            if (position >= line.length) {
                position -= line.length
            }
            if (isTree(line, position)) {
                trees++
            }
            position += right
        }
        downCounter++
    } }
    return trees
}

fun isTree(line: String, pos: Int): Boolean {
    if (line.toCharArray()[pos] == '#') {
        return true
    }
    return false
}