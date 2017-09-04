package vitta.challenge.query.application.handlers

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import vitta.challenge.domain.Id
import vitta.challenge.domain.Territory
import vitta.challenge.query.repository.repositories.TerritoryRepository


@Service class QueryTerritoryHandler(val territoryRepository: TerritoryRepository) {

    fun handleGetTerritories(request: ServerRequest): Mono<ServerResponse>{
        return ServerResponse.ok().body(territoryRepository.findAll(),Territory::class.java)
    }

    fun handleGetTerritoryById(request: ServerRequest): Mono<ServerResponse>{
        return territoryRepository.findOneById(Id(request.pathVariable("id")))
                .flatMap { ServerResponse.ok().body(Mono.just(it),Territory::class.java) }
                .switchIfEmpty( ServerResponse.notFound().build())
    }
}