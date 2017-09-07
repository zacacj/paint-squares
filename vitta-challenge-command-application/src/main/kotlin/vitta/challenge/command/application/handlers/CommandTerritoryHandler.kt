package vitta.challenge.command.application.handlers

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import vitta.challenge.command.application.representation.TerritoryRepresentation
import vitta.challenge.command.repository.CommandTerritoryRepository
import vitta.challenge.domain.Name
import vitta.challenge.domain.Point
import vitta.challenge.domain.Territory
import vitta.challenge.domain.TerritoryId
import java.net.URI


@Service
class CommandTerritoryHandler(val territoryRepository: CommandTerritoryRepository) {

    fun handleAddTerritories(request: ServerRequest): Mono<ServerResponse> {

        val territoryRequest = request.bodyToMono(TerritoryRepresentation::class.java)
        val territoryId = TerritoryId()
       return  territoryRequest
               .doOnSuccess { territoryRepository.save(fromRequestToDomain(it,territoryId)) }
               .map{ TerritoryRepresentation.fromDomain(territoryRepository.get(territoryId))}
               .`as` { ServerResponse.created(URI("")).body(it) }
    }


    private fun fromRequestToDomain(it: TerritoryRepresentation, territoryId: TerritoryId) : Territory{
        return Territory(territoryId = territoryId,
                  name = Name(it.name),
                  start = Point(it.start.x, it.start.y),
                  end = Point(it.end.x, it.end.y)
        )
    }

}