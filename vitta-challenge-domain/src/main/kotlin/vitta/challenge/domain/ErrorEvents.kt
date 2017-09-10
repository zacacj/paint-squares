package vitta.challenge.domain

import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Event
import java.util.*


class ErrorLogged(val errorId: AggregateId = AggregateId(UUID.randomUUID().toString()
), val request: String, val error: String)
    : Event()