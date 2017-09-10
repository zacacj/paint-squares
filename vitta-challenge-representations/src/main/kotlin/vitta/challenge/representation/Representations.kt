package vitta.challenge.representation

import com.fasterxml.jackson.annotation.JsonFilter
import vitta.challenge.domain.Point
import vitta.challenge.domain.Square
import vitta.challenge.domain.Territory
import java.time.LocalDateTime

data class TerritoryRepresentation(val id: String? = null,
                                   val name: String? = null,
                                   val start: PointRepresentation? = null,
                                   val end: PointRepresentation? = null,
                                   val createdAt: LocalDateTime = LocalDateTime.now()
) {


    @JsonFilter("withpainted")
    val painted_points: MutableSet<PointRepresentation> = mutableSetOf()
    var paintedArea: Int = painted_points.size
    val area: Int = if (end != null && end.x != null && end.y != null && start != null && start.x != null && start.y != null)
            (end!!.x!! - start!!.x!!) * (end!!.y!! - start!!.y!!) else 0
    var proportionalArea: Int = if (area == 0) {
        0
    } else paintedArea * 100 / area
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
                    )
            )
        }
    }

    fun addPoint(point: PointRepresentation) {
        painted_points.add(point)
        paintedArea = painted_points.size
        proportionalArea = if (area == 0) {
            0
        } else paintedArea * 100 / area
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


data class ErrorRepresentation(val id: String? = null,
                               val request: String? = null,
                               val cause: String? = "Not informed!",
                               val createdAt: LocalDateTime? = LocalDateTime.now())

data class Totals(val totalArea: Long, val totalPaintedArea: Long)
