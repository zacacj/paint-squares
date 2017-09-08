package vitta.challenge.command.application.handlers

import br.com.zup.eventsourcing.core.Repository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import vitta.challenge.domain.PaintSquare
import vitta.challenge.domain.Point
import vitta.challenge.domain.TerritoryCommandHandler
import vitta.challenge.representation.ErrorRepresentation
import vitta.challenge.representation.SquareRepresentation


@Service
class CommandSquareHandler(val territoryCommandHandler: TerritoryCommandHandler) {

    fun handlePaintSquare(request: ServerRequest): Mono<ServerResponse> {

        val x = request.pathVariable("x")
        val y = request.pathVariable("y")

        return try {
            val square = territoryCommandHandler.handle(PaintSquare(Point(x.toInt(), y.toInt())))
            ServerResponse.ok().body(SquareRepresentation.fromDomain(square).toMono())
        } catch (e: Throwable) {
            when (e) {
                is Repository.NotFoundException -> ServerResponse.notFound().build()
                else -> ServerResponse.badRequest().body(ErrorRepresentation(e.message).toMono())
            }
        }

    }

}
