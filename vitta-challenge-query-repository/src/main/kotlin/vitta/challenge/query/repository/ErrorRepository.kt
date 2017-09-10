package vitta.challenge.query.repository

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import vitta.challenge.representation.ErrorRepresentation


interface ErrorRepository : ReactiveMongoRepository<ErrorRepresentation, String> {
    fun findFirst5ByOrderByCreatedAtDesc(): Flux<ErrorRepresentation>
}
