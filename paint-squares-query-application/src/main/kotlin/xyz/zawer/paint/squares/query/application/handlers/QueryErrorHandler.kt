package xyz.zawer.paint.squares.query.application.handlers

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import xyz.zawer.paint.squares.query.repository.ErrorRepository
import xyz.zawer.paint.squares.representation.ErrorRepresentation


@Service
class QueryErrorHandler(val errorRepository: ErrorRepository) {

    fun handleGetErrors(request: ServerRequest): Mono<ServerResponse> {
        val isLimited = request.queryParam("limited")
        if (isLimited.isPresent) {
            return ServerResponse.ok().body(errorRepository.findFirst5ByOrderByCreatedAtDesc(),
                                            ErrorRepresentation::class.java
            )
        }
        return ServerResponse.ok().body(errorRepository.findAll(),
                                        ErrorRepresentation::class.java
        )
    }

}