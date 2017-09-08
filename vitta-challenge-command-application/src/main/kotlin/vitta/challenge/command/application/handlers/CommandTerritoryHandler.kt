package vitta.challenge.command.application.handlers

import br.com.zup.eventsourcing.core.Repository
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import vitta.challenge.command.application.representation.ErrorRepresentation
import vitta.challenge.command.application.representation.TerritoryRepresentation
import vitta.challenge.domain.CreateTerritory
import vitta.challenge.domain.DeleteTerritory
import vitta.challenge.domain.Name
import vitta.challenge.domain.Point
import vitta.challenge.domain.TerritoryCommandHandler
import vitta.challenge.domain.TerritoryId
import java.net.URI


@Service
class CommandTerritoryHandler(val territoryCommandHandler: TerritoryCommandHandler) {

    fun handleAddTerritories(request: ServerRequest): Mono<ServerResponse> {

        val territoryRequest = request.bodyToMono(TerritoryRepresentation::class.java)
        val territoryId = TerritoryId()

        return territoryRequest.flatMap {
            try {
                it.validate()
                val territory = territoryCommandHandler.handle(fromRequestToCommand(it, territoryId))
                ServerResponse.created(URI("")).contentType(MediaType.APPLICATION_JSON_UTF8).body(
                        TerritoryRepresentation.fromDomain(territory).toMono()
                )
            } catch (e: RuntimeException) {
                ServerResponse.badRequest().body(ErrorRepresentation(e.message).toMono())
            }
        }

    }

    private fun fromRequestToCommand(it: TerritoryRepresentation, territoryId: TerritoryId): CreateTerritory {
        return CreateTerritory(id = territoryId,
                               name = Name(it.name!!),
                               start = Point(it.start!!.x!!, it.start.y!!),
                               end = Point(it.end!!.x!!, it.end.y!!)
        )
    }

    fun handleDeleteTerritories(serverRequest: ServerRequest): Mono<ServerResponse> {
        val id = serverRequest.pathVariable("id")
        return try {
            territoryCommandHandler.handle(DeleteTerritory(TerritoryId(id)))
            ServerResponse.ok().build()
        } catch (e: Throwable) {
            when (e) {
                is Repository.NotFoundException -> ServerResponse.notFound().build()
                else -> ServerResponse.badRequest().body(ErrorRepresentation(e.message).toMono())
            }
        }

    }

}

