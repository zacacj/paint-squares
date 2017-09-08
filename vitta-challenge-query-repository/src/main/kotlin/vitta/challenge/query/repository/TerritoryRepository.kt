package vitta.challenge.query.repository

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import vitta.challenge.representation.TerritoryRepresentation

interface TerritoryRepository : ReactiveMongoRepository<TerritoryRepresentation, String>