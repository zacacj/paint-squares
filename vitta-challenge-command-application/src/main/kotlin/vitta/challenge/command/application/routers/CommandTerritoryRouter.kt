package vitta.challenge.command.application.routers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router
import vitta.challenge.command.application.handlers.CommandSquareHandler
import vitta.challenge.command.application.handlers.CommandTerritoryHandler


@Configuration
class CommandTerritoryRouter(val commandTerritoryHandler: CommandTerritoryHandler, val commandSquareHandler: CommandSquareHandler) {

    @Bean
    fun commandTerritoryRouterFunction(): RouterFunction<*> {
        return router {
            POST("/territories") { commandTerritoryHandler.handleAddTerritories(it) }
            DELETE("/territories/{id}") { commandTerritoryHandler.handleDeleteTerritories(it) }
            PATCH("/squares/{x}/{y}") { commandSquareHandler.handlePaintSquare(it) }
        }
    }

}