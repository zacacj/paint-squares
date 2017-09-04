package vitta.challenge.domain

data class Territory(val id: Id,
                  val name: Name,
                  val start: Point,
                  val end: Point,
                  val area: Area,
                  val paitedArea: Area)

data class Id(val value: String)

data class Name(val value: String)

data class Point(val x: Int, val y: Int)

data class Area(val value: Long)