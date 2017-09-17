package xyz.zawer.paint.squares.command.application.routers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router
import xyz.zawer.paint.squares.command.application.handlers.CommandSquareHandler
import xyz.zawer.paint.squares.command.application.handlers.CommandTerritoryHandler


@Configuration
class CommandTerritoryRouter(val commandTerritoryHandler: CommandTerritoryHandler, val commandSquareHandler: CommandSquareHandler) {

    @Bean
    fun commandTerritoryRouterFunction(): RouterFunction<*> {
        return router {
            POST("/territories") {
                println(it)
                commandTerritoryHandler.handleAddTerritories(it)
            }
            DELETE("/territories/{id}") {
                println(it)
                commandTerritoryHandler.handleDeleteTerritories(it)
            }
            PATCH("/squares/{x}/{y}") {
                println(it)
                commandSquareHandler.handlePaintSquare(it)
            }
        }
    }

}