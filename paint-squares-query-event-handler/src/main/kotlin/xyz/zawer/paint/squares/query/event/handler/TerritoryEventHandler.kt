package xyz.zawer.paint.squares.query.event.handler

import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.AggregateVersion
import br.com.zup.eventsourcing.core.Event
import br.com.zup.eventsourcing.core.EventHandler
import br.com.zup.eventsourcing.core.MetaData
import org.springframework.stereotype.Service
import xyz.zawer.paint.squares.domain.SquarePainted
import xyz.zawer.paint.squares.domain.Territory
import xyz.zawer.paint.squares.domain.TerritoryCreated
import xyz.zawer.paint.squares.domain.TerritoryDeleted
import xyz.zawer.paint.squares.query.repository.TerritoryRepository
import xyz.zawer.paint.squares.representation.PointRepresentation
import xyz.zawer.paint.squares.representation.TerritoryRepresentation


@Service
class TerritoryEventHandler(val territoryRepository: TerritoryRepository
) : EventHandler {
    override fun handle(aggregateId: AggregateId, event: Event, metaData: MetaData, version: AggregateVersion) {
        when (event) {
            is TerritoryCreated -> execute(aggregateId, event, version)
            is TerritoryDeleted -> execute(aggregateId, event, version)
            is SquarePainted -> execute(aggregateId, event, version)
        }
    }

    private fun execute(aggregateId: AggregateId, event: TerritoryCreated, version: AggregateVersion) {

        territoryRepository.save(TerritoryRepresentation.fromDomain(Territory(territoryId = event.territoryId,
                                                                              name = event.name,
                                                                              start = event.start,
                                                                              end = event.end
        )
        )
        ).subscribe()
    }

    private fun execute(aggregateId: AggregateId, event: TerritoryDeleted, version: AggregateVersion) {
        territoryRepository.deleteById(aggregateId.value).subscribe()
    }

    private fun execute(aggregateId: AggregateId, event: SquarePainted, version: AggregateVersion) {
        territoryRepository.findById(aggregateId.value).subscribe {
            it.addPoint(PointRepresentation(event.point.x, event.point.y))
            territoryRepository.save(it).subscribe()
        }
    }

}