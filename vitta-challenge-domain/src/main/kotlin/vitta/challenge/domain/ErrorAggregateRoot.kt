package vitta.challenge.domain

import br.com.zup.eventsourcing.core.AggregateRoot
import br.com.zup.eventsourcing.core.Event


class ErrorAggregateRoot(var request: String, var error: String) : AggregateRoot() {

    init {
        applyChange(ErrorLogged(request = request, error = error))
    }

    override fun applyEvent(event: Event) {
        when (event) {
            is ErrorLogged -> apply(event)
        }
    }

    fun apply(event: ErrorLogged) {
        this.id = event.errorId
        this.request = event.request
        this.error = event.error
    }
}