package xyz.zawer.paint.squares.command.application.handlers

import br.com.zup.eventsourcing.core.Repository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import xyz.zawer.paint.squares.domain.ErrorCommandHandler
import xyz.zawer.paint.squares.domain.LogError
import xyz.zawer.paint.squares.domain.PaintSquare
import xyz.zawer.paint.squares.domain.Point
import xyz.zawer.paint.squares.domain.TerritoryCommandHandler
import xyz.zawer.paint.squares.representation.ErrorRepresentation
import xyz.zawer.paint.squares.representation.SquareRepresentation


@Service
class CommandSquareHandler(val territoryCommandHandler: TerritoryCommandHandler,
                           val errorCommandHandler: ErrorCommandHandler) {

    fun handlePaintSquare(request: ServerRequest): Mono<ServerResponse> {

        val x = request.pathVariable("x")
        val y = request.pathVariable("y")

        return try {
            val square = territoryCommandHandler.handle(PaintSquare(Point(x.toInt(), y.toInt())))
            ServerResponse.ok().body(SquareRepresentation.fromDomain(square).toMono())
        } catch (e: Throwable) {
            errorCommandHandler.handle(
                    LogError(request = request.toString(),
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
