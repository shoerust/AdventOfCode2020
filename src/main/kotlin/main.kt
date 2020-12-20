import java.io.File

val regex = """([0-9]+)-([0-9]+)\s([\w]+):\s([\w]+)""".toRegex()

fun main(args: Array<String>) {
    println("Hello World!")
    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    var validPasswords = 0
    lines.forEach { line -> if (valid(line)) validPasswords += 1 }
    println(validPasswords)
}

fun valid(data: String): Boolean {
    val result = regex.find(data)
    val (pos1, pos2, letter, password) = result!!.destructured
    val letterChar = letter.toCharArray()[0]
    val passwordArray = password.toCharArray()
    if (passwordArray[pos1.toInt()-1] == letterChar && passwordArray[pos2.toInt()-1] != letterChar) {
        return true;
    }
    if (passwordArray[pos1.toInt()-1] != letterChar && passwordArray[pos2.toInt()-1] == letterChar) {
        return true;
    }
    return false
}