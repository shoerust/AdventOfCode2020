import java.io.File

//vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
var regex = """([\w]+\s[\w]+)\sbag""".toRegex()
var numberRegex = """([0-9]+\s[\w]+\s[\w]+)\sbag""".toRegex()

val coloursInner = mutableMapOf<String, Set<String>>()
val coloursOuter = mutableMapOf<String, Set<String>>()
fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()
    for (line in lines) {
        val work = line.replace("bags", "bag")
        val data = work.split(" contain ")
        val keyResult = regex.find(data[0])
        var (key) = keyResult!!.destructured
        key = key.replace("bag", "").trim()
        if (!coloursInner.containsKey(key)) {
            coloursInner[key] = mutableSetOf<String>()
        }
        
        var matchResult = numberRegex.find(data[1])
        while (matchResult != null) {
            val d = matchResult.value.replace("bag", "").trim()
            if (!d.contains("no other")) {
                val test = mutableSetOf<String>()
                test.addAll(coloursInner[key]!!)
                test.add(d)
                coloursInner[key] = test
            }
            matchResult = matchResult.next()

        }
    }
    println("ColoursInner: $coloursInner")
    for (pair in coloursInner) {
        val key = pair.key
        for (v in pair.value) {
            if (coloursOuter.containsKey(v)) {
                val test = mutableSetOf<String>()
                test.addAll(coloursOuter[v]!!)
                test.add(key)
                coloursOuter[v] = test
            } else {
                coloursOuter[v] = mutableSetOf<String>(key)
            }
        }
    }
    println("ColoursOuter: $coloursOuter")

    totalOuter("shiny gold")
    println(colours.size)

    totalInner("shiny gold")
    println(totalInnerColours.size)
    println(total)
}

var totalInnerColours = mutableListOf<String>()
var total = -1
fun totalInner(name: String) {
    if (coloursInner.containsKey(name)) {
        for (b in coloursInner[name]!!) {
            val t = b.split(" ")
            val num = t[0].toInt()
            for (i in 1..num) {
                totalInner(t[1] + " " + t[2])
            }
        }
        total++
    }
}

var colours = mutableSetOf<String>()
fun totalOuter(name: String) {
    if (coloursOuter.containsKey(name)) {
        for (b in coloursOuter[name]!!) {
            totalOuter(b)
        }
        coloursOuter[name]?.let { colours.addAll(it) }
    }
}
