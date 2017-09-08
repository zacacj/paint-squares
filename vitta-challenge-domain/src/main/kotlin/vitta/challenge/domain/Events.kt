package vitta.challenge.domain

import br.com.zup.eventsourcing.core.Event


class TerritoryCreated(val territoryId: TerritoryId,
                       val name: Name,
                       val start: Point,
                       val end: Point) : Event()

class TerritoryDeleted : Event()
class SquarePainted(val point: Point) : Event()
class SquareAdded(val square: Square): Event()
class SquareDeleted(val square: Square): Event()
class PlaneCreated(): Event()