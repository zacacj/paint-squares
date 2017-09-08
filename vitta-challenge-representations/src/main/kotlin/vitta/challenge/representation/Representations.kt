package vitta.challenge.representation

import vitta.challenge.domain.Point
import vitta.challenge.domain.Square
import vitta.challenge.domain.Territory

data class TerritoryRepresentation(val id: String? = null,
                                   val name: String? = null,
                                   val start: PointRepresentation? = null,
                                   val end: PointRepresentation? = null,
                                   val area: Int? = null,
                                   val painted_area: Int? = null,
                                   val painted_points: MutableSet<PointRepresentation> = mutableSetOf()) {

    fun validate() {
        if (null == name || name == "")
            throw NameMustBeValid()
        if (null == start)
            throw StartMustBeInformed()
        if (null == end)
            throw EndMustBeInformed()
        start.validate()
        end.validate()
        if (end.x!! <= start.x!!)
            throw StartMustNotBeGreaterThenEnd("x", start.x, end.x)
        if (end.y!! <= start.y!!)
            throw StartMustNotBeGreaterThenEnd("x", start.y, end.y)

    }

    companion object {
        fun fromDomain(territory: Territory): TerritoryRepresentation {
            return TerritoryRepresentation(
                    id = territory.id.value,
                    name = territory.name!!.value,
                    start = PointRepresentation.fromDomain(
                            territory.start!!
                    ),
                    end = PointRepresentation.fromDomain(
                            territory.end!!
                    ),
                    area = territory.area.value,
                    painted_area = territory.paintedArea.value
            )
        }
    }
}

class StartMustNotBeGreaterThenEnd(s: String, x: Int, y: Int) :
        RuntimeException("coordenate $s: start: $x, greater or equals to end: $y")

class NameMustBeValid : RuntimeException("Name must be informed")
class StartMustBeInformed : RuntimeException("Start must be informed")
class EndMustBeInformed : RuntimeException("End must be informed")

data class PointRepresentation(val x: Int? = null,
                               val y: Int? = null) {
    companion object {
        fun fromDomain(point: Point): PointRepresentation {
            return PointRepresentation(point.x, point.y)
        }
    }

    fun validate() {
        if (null == x)
            throw XMustBeInformed()
        if (null == y)
            throw YMustBeInformed()
        if (x < 0)
            throw XMustBeGreaterThenZero()
        if (y < 0)
            throw YMustBeGreaterThenZero()
    }
}

data class SquareRepresentation(val x: Int, val y: Int, val painted: Boolean) {
    companion object {
        fun fromDomain(square: Square): SquareRepresentation {
            return SquareRepresentation(square.point.x, square.point.y,
                                        square.painted.value
            )
        }
    }
}

class XMustBeInformed : RuntimeException("X must be informed")
class YMustBeInformed : RuntimeException("Y must be informed")
class XMustBeGreaterThenZero : RuntimeException("X must be greater or equals to then zero")
class YMustBeGreaterThenZero : RuntimeException("Y must be greater or equals to then zero")


data class ErrorRepresentation(val cause: String? = "Not informed!")
