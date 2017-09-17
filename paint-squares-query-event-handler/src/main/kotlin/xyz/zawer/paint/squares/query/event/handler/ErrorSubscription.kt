package xyz.zawer.paint.squares.query.event.handler

import br.com.zup.eventsourcing.eventstore.PersistentAggregateSubscriber
import org.springframework.stereotype.Service
import xyz.zawer.paint.squares.domain.ErrorAggregateRoot


@Service
class ErrorSubscription(eventHandler: ErrorEventHandler) : PersistentAggregateSubscriber<ErrorAggregateRoot>(
        eventHandler = eventHandler
)