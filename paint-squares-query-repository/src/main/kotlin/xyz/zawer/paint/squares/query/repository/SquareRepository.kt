package xyz.zawer.paint.squares.query.repository

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono
import xyz.zawer.paint.squares.domain.Point
import xyz.zawer.paint.squares.domain.Square


interface SquareRepository : ReactiveMongoRepository<Square, Point> {

    fun findOneByPoint(point : Point): Mono<Square>

}