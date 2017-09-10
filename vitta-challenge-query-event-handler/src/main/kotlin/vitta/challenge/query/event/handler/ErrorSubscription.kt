package vitta.challenge.query.event.handler

import br.com.zup.eventsourcing.eventstore.PersistentAggregateSubscriber
import org.springframework.stereotype.Service
import vitta.challenge.domain.ErrorAggregateRoot


@Service
class ErrorSubscription(eventHandler: ErrorEventHandler) : PersistentAggregateSubscriber<ErrorAggregateRoot>(
        eventHandler = eventHandler
)