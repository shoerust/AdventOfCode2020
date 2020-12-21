import java.io.File

val regex = """(.+):(.+)""".toRegex()
val requiredKeys = setOf<String>("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
val cmRegex = """([0-9]+)cm""".toRegex()
val inchRegex = """([0-9]+)in""".toRegex()
val colorRegex = """#([0-9]|[a-f]){6}""".toRegex()
val eyeRegex = """^(amb|blu|brn|gry|grn|hzl|oth)${'$'}""".toRegex()
val pidRegex = """[0-9]{9}""".toRegex()
fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    var validPassports = 0
    var passport = ""
    var rk = mutableSetOf<String>()
    for (line in lines) {
        if (line.isNotBlank()) {
            passport += " $line"
        } else {
            rk = validate(passport.trim())
            if (rk.isEmpty()) {
                validPassports++
            }
            passport = ""
        }
    }
    if (rk.isEmpty()) {
        validPassports++
    }
    println(validPassports)
}

fun validate(passport: String): MutableSet<String> {

    val temp = mutableSetOf<String>()
    temp.addAll(requiredKeys)

    val pairs = passport.split(" ")
    for (pair in pairs) {
        val match = regex.find(pair)
        val (key, value) = match!!.destructured
        if (requiredKeys.contains(key)) {
            if (key == "byr") {
              if (value.toInt() in 1920..2002) {
                  temp.remove(key)
              }
            } else if (key == "iyr") {
                if (value.toInt() in 2010..2020) {
                    temp.remove(key)
                }
            } else if (key == "eyr") {
                if (value.toInt() in 2020..2030) {
                    temp.remove(key)
                }
            } else if (key == "hgt") {
                val cm = cmRegex.find(value)
                if (cm != null) {
                    val (number) = cm.destructured
                    if (number.toInt() in 150..193) {
                        temp.remove(key)
                    }
                } else {
                    val inch = inchRegex.find(value)
                    if (inch != null) {
                        val (number) = inch.destructured
                        if (number.toInt() in 59..76) {
                            temp.remove(key)
                        }
                    }
                }
            } else if (key == "hcl") {
                if (colorRegex.matches(value)) {
                    temp.remove(key)
                }
            } else if (key == "ecl") {
               if (eyeRegex.matches(value)) {
                   temp.remove(key)
               }
            } else if (key == "pid") {
              if (pidRegex.matches(value)) {
                  temp.remove(key)
              }
            }
        }
    }
    return temp
}
