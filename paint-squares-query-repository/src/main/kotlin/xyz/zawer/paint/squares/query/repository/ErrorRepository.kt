package xyz.zawer.paint.squares.query.repository

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import xyz.zawer.paint.squares.representation.ErrorRepresentation


interface ErrorRepository : ReactiveMongoRepository<ErrorRepresentation, String> {
    fun findFirst5ByOrderByCreatedAtDesc(): Flux<ErrorRepresentation>
}
