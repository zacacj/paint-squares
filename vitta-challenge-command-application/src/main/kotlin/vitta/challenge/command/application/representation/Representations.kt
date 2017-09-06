package vitta.challenge.command.application.representation

import org.hibernate.validator.constraints.NotBlank
import vitta.challenge.domain.Point
import vitta.challenge.domain.Territory
import javax.validation.Valid
import javax.validation.constraints.NotNull


data class TerritoryRepresentation(val id: String? = null,
                                   @field:NotBlank val name: String,
                                   @field:[NotNull Valid] val start: PointRepresentation,
                                   @field:[NotNull Valid] val end: PointRepresentation,
                                   val area: Int? = null,
                                   val paited_area: Int? = null) {
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
                    paited_area = territory.paintedArea.value
            )
        }
    }
}

data class PointRepresentation(@field:NotNull val x: Int,
                               @field:NotNull val y: Int) {
    companion object {
        fun fromDomain(point: Point): PointRepresentation {
            return PointRepresentation(point.x, point.y)
        }
    }
}