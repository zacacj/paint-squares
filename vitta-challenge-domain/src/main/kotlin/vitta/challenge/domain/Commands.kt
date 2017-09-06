package vitta.challenge.domain


data class CreateTerritory(val id: TerritoryId = TerritoryId(),
                           val name: Name,
                           val start: Point,
                           val end: Point)

data class DeleteTerritory(val id: TerritoryId)

data class PaintSquare(val point: Point)