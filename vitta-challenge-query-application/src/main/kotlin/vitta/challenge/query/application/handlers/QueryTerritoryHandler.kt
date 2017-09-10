package vitta.challenge.query.application.handlers

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import vitta.challenge.query.repository.TerritoryRepository
import vitta.challenge.representation.TerritoryRepresentation
import vitta.challenge.representation.Totals


@Service
class QueryTerritoryHandler(val territoryRepository: TerritoryRepository, val template: ReactiveMongoTemplate) {

    fun handleGetTerritories(request: ServerRequest): Mono<ServerResponse> {
        val isOrdered = request.queryParam("ordered")
        if (isOrdered.isPresent) {
            if (isOrdered.get() == "area") {
                return ServerResponse.ok().body(territoryRepository.findByOrderByPaintedAreaDesc(),
                                                TerritoryRepresentation::class.java
                )
            }
            if (isOrdered.get() == "proportional") {
                return ServerResponse.ok().body(territoryRepository.findByOrderByProportionalAreaDesc(),
                                                TerritoryRepresentation::class.java
                )
            }
        }
        val isLimited = request.queryParam("limited")
        if (isLimited.isPresent) {
            return ServerResponse.ok().body(territoryRepository.findFirst5ByOrderByCreatedAtDesc(),
                                            TerritoryRepresentation::class.java
            )
        }
        val withpainted = request.queryParam("withpainted")
        val isWithPainted = if (withpainted.isPresent) {
            withpainted.get() == "true"
        } else false

        return ServerResponse.ok().body(territoryRepository.findAll().map { it -> it.withPainted(isWithPainted) },
                                        TerritoryRepresentation::class.java
        )
    }

    fun handleGetTerritoryById(request: ServerRequest): Mono<ServerResponse> {

        val withpainted = request.queryParam("withpainted")
        val isWithPainted = if (withpainted.isPresent) {
            withpainted.get() == "true"
        } else false
        return territoryRepository.findById(request.pathVariable("id"))
                .flatMap {
                    ServerResponse.ok().body(Mono.just(it.withPainted(isWithPainted)),
                                             TerritoryRepresentation::class.java
                    )
                }
                .switchIfEmpty(ServerResponse.notFound().build())
    }

    fun handleGetTotals(): Mono<ServerResponse> {
        val matchStage = Aggregation.match(Criteria("_id").exists(true))
        val groupStage = Aggregation.group().sum("area").`as`("totalArea")
                .sum("paintedArea").`as`("totalPaintedArea")
        val projectionStage = Aggregation.project("totalArea", "totalPaintedArea")
        val aggregation = Aggregation.newAggregation(matchStage, groupStage, projectionStage)
        val totals = template.aggregate(aggregation,
                                        TerritoryRepresentation::class.java,
                                        Totals::class.java
        )

        return ServerResponse.ok().body(totals.last(), Totals::class.java)
    }
}