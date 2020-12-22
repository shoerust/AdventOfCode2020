import java.io.File
import java.lang.Math.abs

val regex = """([N|S|E|W|L|R|F])([0-9]+)""".toRegex()
fun main(args: Array<String>) {
    println("Hello AdventOfCode2020!")

    val fileName = "/Volumes/AsphyxiaSSD/development/advent1/src/main/resources/input.txt"
    val lines: List<String> = File(fileName).readLines()

    for ((index, line) in lines.withIndex()) {
        var matchResult = regex.find(line)
        val direction = matchResult!!.groupValues[1]
        val value = matchResult!!.groupValues[2].toLong()

        when (direction) {
            "N" -> {
                Ship.y = Ship.y + value
            }
            "S" -> {
                Ship.y = Ship.y - value
            }
            "E" -> {
                Ship.x = Ship.x + value
            }
            "W" -> {
                Ship.x = Ship.x - value
            }
            "L" -> {
                rotateLeft(value)
            }
            "R" -> {

                rotateRight(value)
            }
            "F" -> {
                when (Ship.direction) {
                    "N" -> {
                        Ship.y = Ship.y + value
                    }
                    "S" -> {
                        Ship.y = Ship.y - value
                    }
                    "E" -> {
                        Ship.x = Ship.x + value
                    }
                    "W" -> {
                        Ship.x = Ship.x - value
                    }
                }
            }
            else -> {
                println("wtf")
            }
        }
    }
    val result = kotlin.math.abs(Ship.x) + kotlin.math.abs(Ship.y)
    println("x: ${kotlin.math.abs(Ship.x)}, y: ${kotlin.math.abs(Ship.y)}, result: $result")
}

fun rotateLeft(value: Long) {
    when (value) {
        90L -> {
            when (Ship.direction) {
                "N" -> {
                    Ship.direction = "W"
                }
                "E" -> {
                    Ship.direction = "N"
                }
                "S" -> {
                    Ship.direction = "E"
                }
                "W" -> {
                    Ship.direction = "S"
                }
            }
        }
        180L -> {
            when (Ship.direction) {
                "N" -> {
                    Ship.direction = "S"
                }
                "E" -> {
                    Ship.direction = "W"
                }
                "S" -> {
                    Ship.direction = "N"
                }
                "W" -> {
                    Ship.direction = "E"
                }
            }
        }
        270L -> {
            when (Ship.direction) {
                "N" -> {
                    Ship.direction = "E"
                }
                "E" -> {
                    Ship.direction = "S"
                }
                "S" -> {
                    Ship.direction = "W"
                }
                "W" -> {
                    Ship.direction = "N"
                }
            }
        }
    }
}

fun rotateRight(value: Long) {
    when (value) {
        90L -> {
            when (Ship.direction) {
                "N" -> {
                    Ship.direction = "E"
                }
                "E" -> {
                    Ship.direction = "S"
                }
                "S" -> {
                    Ship.direction = "W"
                }
                "W" -> {
                    Ship.direction = "N"
                }
            }
        }
        180L -> {
            when (Ship.direction) {
                "N" -> {
                    Ship.direction = "S"
                }
                "E" -> {
                    Ship.direction = "W"
                }
                "S" -> {
                    Ship.direction = "N"
                }
                "W" -> {
                    Ship.direction = "E"
                }
            }
        }
        270L -> {
            when (Ship.direction) {
                "N" -> {
                    Ship.direction = "W"
                }
                "E" -> {
                    Ship.direction = "N"
                }
                "S" -> {
                    Ship.direction = "E"
                }
                "W" -> {
                    Ship.direction = "S"
                }
            }
        }
    }
}

fun move(value: Long, direction: String) {
    when (direction) {
        "N" -> {
            Ship.y = Ship.y + value
        }
        "S" -> {
            Ship.y = Ship.y - value
        }
        "E" -> {
            Ship.x = Ship.x + value
        }
        "W" -> {
            Ship.x = Ship.x - value
        }
    }
}

object Ship {
    var direction = "E"
    var x = 0L
    var y = 0L
}

