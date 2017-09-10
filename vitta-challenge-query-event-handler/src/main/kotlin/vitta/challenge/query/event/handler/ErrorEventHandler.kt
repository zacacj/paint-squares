package vitta.challenge.query.event.handler

import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.AggregateVersion
import br.com.zup.eventsourcing.core.Event
import br.com.zup.eventsourcing.core.EventHandler
import br.com.zup.eventsourcing.core.MetaData
import org.springframework.stereotype.Service
import vitta.challenge.domain.ErrorLogged
import vitta.challenge.query.repository.ErrorRepository
import vitta.challenge.representation.ErrorRepresentation


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