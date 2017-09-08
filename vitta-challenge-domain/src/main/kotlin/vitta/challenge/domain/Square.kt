package vitta.challenge.domain

import br.com.zup.eventsourcing.core.AggregateId
import java.io.Serializable
import java.util.*


data class Square(val territoryId: TerritoryId? = null,
                  val point: Point,
                  val painted: Painted) {
}

data class Painted(val value: Boolean)

class TerritoryId(value: String = UUID.randomUUID().toString()): AggregateId(value), Serializable

data class Name(val value: String)

data class Point(val x: Int, val y: Int)

data class Area(val value: Int)

enum class Status{
    ACTIVE,
    DELETED
}