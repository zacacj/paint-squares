package xyz.zawer.paint.squares.query.event.handler

import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.AggregateVersion
import br.com.zup.eventsourcing.core.Event
import br.com.zup.eventsourcing.core.EventHandler
import br.com.zup.eventsourcing.core.MetaData
import org.springframework.stereotype.Service
import xyz.zawer.paint.squares.domain.ErrorLogged
import xyz.zawer.paint.squares.query.repository.ErrorRepository
import xyz.zawer.paint.squares.representation.ErrorRepresentation


@Service
class ErrorEventHandler(val errorRepository: ErrorRepository) : EventHandler {
    override fun handle(aggregateId: AggregateId, event: Event, metaData: MetaData, version: AggregateVersion) {
        when (event) {
            is ErrorLogged -> execute(aggregateId, event, version)
        }
    }

    private fun execute(aggregateId: AggregateId, event: ErrorLogged, version: AggregateVersion) {

        errorRepository.save(
                ErrorRepresentation(id = aggregateId.value, request = event.request, cause = event.error)
        ).subscribe()
    }
}