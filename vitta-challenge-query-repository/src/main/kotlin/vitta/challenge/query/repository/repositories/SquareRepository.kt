package vitta.challenge.query.repository.repositories

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono
import vitta.challenge.domain.Point
import vitta.challenge.domain.Square


interface SquareRepository : ReactiveMongoRepository<Square, Point> {

    fun findOneByPoint(point : Point): Mono<Square>

}