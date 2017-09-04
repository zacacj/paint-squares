package vitta.challenge.domain

data class Territory(val id: Id,
                     val name: Name,
                     val start: Point,
                     val end: Point,
                     private val width: Int = Math.abs(end.x - start.x),
                     private val height: Int = Math.abs(end.y - start.y),
                     private val squaresPainted: MutableList<Square> = mutableListOf()
                     ) {
    val paintedArea: Area
        get() = Area(squaresPainted.count())
    val area: Area
        get() = Area(width * height)
}

data class Id(val value: String)

data class Name(val value: String)

data class Point(val x: Int, val y: Int)

data class Area(val value: Int)