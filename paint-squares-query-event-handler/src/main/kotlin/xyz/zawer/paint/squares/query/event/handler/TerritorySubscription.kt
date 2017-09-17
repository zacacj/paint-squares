package xyz.zawer.paint.squares.query.event.handler

import br.com.zup.eventsourcing.eventstore.PersistentAggregateSubscriber
import org.springframework.stereotype.Service
import xyz.zawer.paint.squares.domain.Territory


@Service
class TerritorySubscription(eventHandler: TerritoryEventHandler) : PersistentAggregateSubscriber<Territory>(
        eventHandler = eventHandler
)