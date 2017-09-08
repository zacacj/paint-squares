package vitta.challenge.query.application.handlers

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import vitta.challenge.query.repository.TerritoryRepository
import vitta.challenge.representation.TerritoryRepresentation

@Service
class QueryTerritoryHandler(val territoryRepository: TerritoryRepository) {

    fun handleGetTerritories(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(territoryRepository.findAll(),
                                        TerritoryRepresentation::class.java
        )
    }

    fun handleGetTerritoryById(request: ServerRequest): Mono<ServerResponse> {
        return territoryRepository.findById(request.pathVariable("id"))
                .flatMap {
                    ServerResponse.ok().body(Mono.just(it),
                                             TerritoryRepresentation::class.java
                    )
                }
                .switchIfEmpty(ServerResponse.notFound().build())
    }
}