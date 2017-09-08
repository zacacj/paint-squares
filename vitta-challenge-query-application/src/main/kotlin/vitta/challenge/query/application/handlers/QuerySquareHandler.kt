package vitta.challenge.query.application.handlers

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import vitta.challenge.domain.Point
import vitta.challenge.query.repository.SquareRepository
import vitta.challenge.representation.SquareRepresentation

@Service
class QuerySquareHandler(val squareRepository: SquareRepository) {
    fun handleGetSquareById(request: ServerRequest): Mono<ServerResponse> {
        return squareRepository.findOneByPoint(Point(x = request.pathVariable("x").toInt(),
                                                     y = request.pathVariable("y").toInt()
        )
        )
                .flatMap {
                    ServerResponse.ok().body(Mono.just(SquareRepresentation.fromDomain(it)),
                                             SquareRepresentation::class.java
                    )
                }
                .switchIfEmpty(ServerResponse.notFound().build())
    }
}