package vitta.challenge.query.event.handler

import br.com.zup.eventsourcing.core.EventHandler
import br.com.zup.eventsourcing.eventstore.PersistentAggregateSubscriber
import org.springframework.stereotype.Service
import vitta.challenge.domain.Territory


@Service
class TerritorySubscription(eventHandler: EventHandler) : PersistentAggregateSubscriber<Territory>(
        eventHandler = eventHandler
)