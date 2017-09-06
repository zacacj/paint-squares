package vitta.challenge.domain

import br.com.zup.eventsourcing.core.AggregateRoot
import br.com.zup.eventsourcing.core.Event

class Territory() : AggregateRoot() {

    var name: Name? = null
    var status: Status = Status.ACTIVE
    var start: Point? = null
    var end: Point? = null
    private var width: Int? = null
    private var height: Int? = null
    private var squaresPainted: MutableMap<Point, Square> = mutableMapOf()

    val paintedArea: Area
        get() = Area(squaresPainted.count())
    val area: Area
        get() = Area(width!! * height!!)

    constructor(territoryId: TerritoryId, name: Name, start: Point, end: Point) : this() {
        applyChange(TerritoryCreated(territoryId, name, start, end))
    }

    override fun applyEvent(event: Event) {
        this.event = event
        if (event is TerritoryCreated) apply(event)
        if (event is TerritoryDeleted) apply(event)
        if (event is SquarePainted) apply(event)
    }

    fun apply(event: TerritoryCreated) {
        this.id = event.territoryId
        this.name = event.name
        this.start = event.start
        this.end = event.end
        this.width = Math.abs(end!!.x - start!!.x)
        this.height = Math.abs(end!!.y - start!!.y)
    }

    fun apply(event: TerritoryDeleted) {
        status = Status.DELETED
    }

    fun apply(event: SquarePainted) {
        squaresPainted.put(event.point, Square(event.point, Painted(true)))
    }

    fun delete() {
        applyChange(TerritoryDeleted())
    }

    fun paint(point: Point) {
        applyChange(SquarePainted(point))
    }
}