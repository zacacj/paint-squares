package vitta.challenge.query.application.routes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router
import vitta.challenge.query.application.handlers.QueryErrorHandler


@Configuration
class QueryErrorRoutes(val queryErrorHandler: QueryErrorHandler) {

    @Bean
    fun queryErrorRouterFunction(): RouterFunction<*> {
        return router {
            GET("/errors") { queryErrorHandler.handleGetErrors(it) }
        }

    }

}