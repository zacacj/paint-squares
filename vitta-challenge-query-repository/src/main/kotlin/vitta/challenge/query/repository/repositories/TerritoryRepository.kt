package vitta.challenge.query.repository.repositories

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono
import vitta.challenge.domain.Id
import vitta.challenge.domain.Territory

interface TerritoryRepository : ReactiveMongoRepository<Territory, Id> {

    fun findOneById(Id: Id): Mono<Territory>

}