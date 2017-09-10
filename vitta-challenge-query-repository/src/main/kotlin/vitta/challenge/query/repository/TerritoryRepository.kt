package vitta.challenge.query.repository

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import vitta.challenge.representation.TerritoryRepresentation

interface TerritoryRepository : ReactiveMongoRepository<TerritoryRepresentation, String> {

    fun findByOrderByPaintedAreaDesc(): Flux<TerritoryRepresentation>
    fun findByOrderByProportionalAreaDesc(): Flux<TerritoryRepresentation>
    fun findFirst5ByOrderByCreatedAtDesc(): Flux<TerritoryRepresentation>
}