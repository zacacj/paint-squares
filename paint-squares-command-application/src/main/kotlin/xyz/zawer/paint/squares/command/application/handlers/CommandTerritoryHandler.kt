package xyz.zawer.paint.squares.command.application.handlers

import br.com.zup.eventsourcing.core.Repository
import br.com.zup.eventsourcing.core.config.objectToJson
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import xyz.zawer.paint.squares.domain.CreateTerritory
import xyz.zawer.paint.squares.domain.DeleteTerritory
import xyz.zawer.paint.squares.domain.ErrorCommandHandler
import xyz.zawer.paint.squares.domain.LogError
import xyz.zawer.paint.squares.domain.Name
import xyz.zawer.paint.squares.domain.Point
import xyz.zawer.paint.squares.domain.TerritoryCommandHandler
import xyz.zawer.paint.squares.domain.TerritoryId
import xyz.zawer.paint.squares.representation.ErrorRepresentation
import xyz.zawer.paint.squares.representation.TerritoryRepresentation
import java.net.URI


@Service
class
CommandTerritoryHandler(val territoryCommandHandler: TerritoryCommandHandler,
                        val errorCommandHandler: ErrorCommandHandler) {

    fun handleAddTerritories(request: ServerRequest): Mono<ServerResponse> {

        val territoryRequest = request.bodyToMono(TerritoryRepresentation::class.java)
                .doOnError {
                    errorCommandHandler.handle(
                            LogError(request = request.toString(),
                                     error = it.message.objectToJson()
                            )
                    )
                }

        val territoryId = TerritoryId()

        return territoryRequest.flatMap {
            try {
                it.validate()
                val territory = territoryCommandHandler.handle(fromRequestToCommand(it, territoryId))
                ServerResponse.created(URI("")).contentType(MediaType.APPLICATION_JSON_UTF8).body(
                        TerritoryRepresentation.fromDomain(territory).toMono()
                )
            } catch (e: RuntimeException) {
                errorCommandHandler.handle(
                        LogError(request = request.toString() + "{$it}",
                                 error = e.message ?: e.javaClass.simpleName
                        )
                )

                ServerResponse.badRequest().body(ErrorRepresentation(e.message).toMono())
            }
        }

    }

    private fun fromRequestToCommand(it: TerritoryRepresentation, territoryId: TerritoryId): CreateTerritory {
        return CreateTerritory(id = territoryId,
                               name = Name(it.name!!),
                               start = Point(it.start!!.x!!, it.start!!.y!!),
                               end = Point(it.end!!.x!!, it.end!!.y!!)
        )
    }

    fun handleDeleteTerritories(serverRequest: ServerRequest): Mono<ServerResponse> {
        val id = serverRequest.pathVariable("id")
        return try {
            territoryCommandHandler.handle(DeleteTerritory(TerritoryId(id)))
            ServerResponse.ok().build()
        } catch (e: Throwable) {
            errorCommandHandler.handle(
                    LogError(request = serverRequest.toString(),
                             error = e.message ?: e.javaClass.simpleName
                    )
            )
            when (e) {
                is Repository.NotFoundException -> ServerResponse.notFound().build()
                else -> ServerResponse.badRequest().body(ErrorRepresentation(e.message).toMono())
            }
        }

    }

}

