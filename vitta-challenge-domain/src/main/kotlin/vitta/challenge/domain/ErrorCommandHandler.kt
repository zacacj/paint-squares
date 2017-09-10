package vitta.challenge.domain

import br.com.zup.eventsourcing.core.Repository


class ErrorCommandHandler(val errorRepository: Repository<ErrorAggregateRoot>) {

    fun handle(command: LogError) {
        val error = ErrorAggregateRoot(command.request, command.error)
        errorRepository.save(error)
    }
}