package vitta.challenge.domain


data class CreateTerritory(val id: TerritoryId = TerritoryId(),
                           val name: Name,
                           val start: Point,
                           val end: Point)

data class AddSquare(val square: Square)

data class DeleteSquare(val square: Square)

data class DeleteTerritory(val id: TerritoryId)

data class PaintSquare(val point: Point)