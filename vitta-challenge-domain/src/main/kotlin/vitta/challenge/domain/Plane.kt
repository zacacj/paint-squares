package vitta.challenge.domain

import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.AggregateRoot
import br.com.zup.eventsourcing.core.Event


class Plane : AggregateRoot() {
    var area = Array(300) { arrayOfNulls<Square>(300) }

    init {
        applyChange(PlaneCreated())
    }

    override fun applyEvent(event: Event) {
        this.event = event
        if (event is PlaneCreated) apply(event)
        if (event is SquareAdded) apply(event)
        if (event is SquareDeleted) apply(event)
    }

    fun apply(planeCreated: PlaneCreated) {
        id = AggregateId("total")
    }

    fun apply(squareAdded: SquareAdded) {
        area[squareAdded.square.point.x][squareAdded.square.point.y] = squareAdded.square
    }

    fun apply(squareDeleted: SquareDeleted){
        area[squareDeleted.square.point.x][squareDeleted.square.point.y] = null
    }

    fun addSquare(command: AddSquare) {
        if (area[command.square.point.x][command.square.point.y] != null)
            throw SquareOverlapping()
        applyChange(SquareAdded(command.square))
    }

    class SquareOverlapping : RuntimeException("Area has been overlapped")
    class InconsistentSquare : RuntimeException("Some went wrong, trying to remove some square that is not there")

    fun deleteSquare(command: DeleteSquare) {
        if (area[command.square.point.x][command.square.point.y] == null)
            throw InconsistentSquare()
        applyChange(SquareDeleted(command.square))
    }
}