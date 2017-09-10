package vitta.challenge.query.application.routes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router
import vitta.challenge.query.application.handlers.QueryTerritoryHandler


@Configuration
class QueryTerritoryRoutes(val queryTerritoryHandler: QueryTerritoryHandler) {

    @Bean
    fun queryTerritoryRouterFunction(): RouterFunction<*> {
        return router {
            GET("/territories") { queryTerritoryHandler.handleGetTerritories(it) }
            GET("/territories/{id}") { queryTerritoryHandler.handleGetTerritoryById(it) }
            GET("/totals") { queryTerritoryHandler.handleGetTotals() }
        }

    }

}